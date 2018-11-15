package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Book.Literature;
import datebases.LiteratureBase;

public class Literatureservice {
	LiteratureBase literaturebase = new LiteratureBase();
	
	public boolean addLiterature(Literature literature){
		if(literaturebase.findCount(literature.getName()) > 0){
			return false;
		}
		literaturebase.add(literature);
		return true;
	}
	public List<Literature> findAllList(){
		return literaturebase.findAllList();
	}
	public boolean deleteLiterature(String name){
		return literaturebase.delete(name);
	}
	public boolean updateLiterature(Literature literature){
		if(literaturebase.findCount(literature.getName()) > 0){
			return false;
		}
		return literaturebase.update(literature);
	}
	public Literature findLiterature(String name) {
		return literaturebase.find(name);
	}
	public boolean addList(ArrayList<Literature> literatureList) {
		Iterator<Literature> litrList =  literatureList.iterator();
		
		while(litrList.hasNext()){
			Literature litera = litrList.next();
			literaturebase.add(litera);
		}
		return true;
	}
	
}
