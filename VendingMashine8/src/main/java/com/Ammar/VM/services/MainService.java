package com.Ammar.VM.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Ammar.VM.models.Item;
import com.Ammar.VM.models.Position;
import com.Ammar.VM.repositories.ItemRepo;
import com.Ammar.VM.repositories.PositionRepo;


@Service
public class MainService {
	private final ItemRepo itemRepo;
	private final PositionRepo positionRepo;
	
	public MainService(ItemRepo itemRepo, PositionRepo positionRepo) {
		super();
		this.itemRepo = itemRepo;
		this.positionRepo = positionRepo;
	}
	//initiation
	 public void initiation(int slots){
		 if(positionRepo.findAll().size()!=slots) {
			 for(int i=0;i<slots;i++) {
				 Position position=new Position(i,0);
				 positionRepo.save(position);
			 }
		 }
	 }
	 //creating
	 	public Item creatitem(Item item,int number) {
	 		Position pos=this.findPositionByNumber(number);
	 		pos.setQuantity(item.getQuantity());
 			boolean exists=itemRepo.findAll().stream().anyMatch(p->p.getName().equals(item.getName())&& p.getCost()==item.getCost());
 			if(exists) {
				Item it=this.findItemByName(item.getName());
				it.setQuantity(it.getQuantity()+item.getQuantity());
				List<Position> temp=new ArrayList<Position>();
				temp=it.getPositions();
				temp.add(pos);
				it.setPositions(temp);
 			}
 			else {
 				List<Position> temp=new ArrayList<Position>();
				temp.add(pos);
				item.setPositions(temp);
 				itemRepo.save(item);
 			}
 			pos.setItem(item);
// 			System.out.println("done");
// 			System.out.println(pos.getId());
 			positionRepo.save(pos);
 			
	 		return item;
	 	}
	//delete
		public void deleteItem(Long id) {
			for(Position pos:positionRepo.findAll())
				if(pos.getItem()==itemRepo.findById(id).get()) {
					pos.setItem(null);
				}
			itemRepo.deleteById(id);
		}
		
	//add new Item
		public void addStuff(Item stuff) {
			itemRepo.save(stuff);
		}
		
	//find Item
		public Item findItemByName(String name) {
			return (Item) itemRepo.findByName(name);
		}
		public Item findStuffByid(Long id) {
			return itemRepo.findById(id).orElse(null);
		}
		
		
		public Item findItemByPostion(Position pos) {
			List<Item> allItem=itemRepo.findAll();
//			List<Item> item=allItem.stream()
//					.filter(p->p.getPositions().
//							toString().
//							contains(pos)).
//					collect(Collectors.toList());
			for(Item target:allItem) {
				for(Position slot:target.getPositions()) {
					if(slot==pos) {
						return target;
					}
				}
			}
			return null;
		}
	//find poition
		public Position findPositionByNumber(int number) {
			Optional<Position> position=positionRepo.findByNumber(number);
			return position.orElse(null);
		}
		
		
		public List<Position> findEmpty(){
			List<Position> positions=positionRepo.findAll();
//			List<Position> areEmpty=(List<Position>) positions.stream().filter(p->p.getQuantity()==0);
			List<Position> areEmpty=new ArrayList<Position>();
			for(Position p:positions) {
				if(p.getItem()==null) {
					areEmpty.add(p);
				}
			}
			return areEmpty;
		}
		public List<Position> allposition() {
			return positionRepo.findAll();
		}
		
		//editing
		public void quanttity(Item item,int number,int change) {
			item.setQuantity(item.getQuantity()+change);
			Position position=this.findPositionByNumber(number);
			position.setQuantity(position.getQuantity()+change);
			itemRepo.save(item);
			positionRepo.save(position);
			if(position.getQuantity()==0) {
				position.setItem(null);
				item.setPositions(null);
			}
			if (item.getQuantity()==0) {
				this.deleteItem(item.getId());
			}
		}
		public void quanttity(Item item,int number) {
			Position pos=this.findPositionByNumber(number);
			item.setQuantity(item.getQuantity()-pos.getQuantity());
			pos.setItem(null);
			positionRepo.save(pos);
			itemRepo.save(item);
			if (item.getQuantity()==0) {
				this.deleteItem(item.getId());
			}
		}

		public void edititem(Item item,int number) {
			Position pos=this.findPositionByNumber(number);
			this.quanttity(pos.getItem(), number);
			this.creatitem(item, number);
			
			
		}
		
		
		
		
	

	
}
