package CSD201x_ASM3;

import java.util.Scanner;

public class personManagement{
	Scanner cs=new Scanner(System.in);
    
public void addNewPerson(Person newperson,MyBSTree list) {
	//Thêm id
	boolean isDuplicateId = false;
	System.out.println("Nhập id:");
	String id=cs.nextLine();
	Node<Person>found=list.find(list.root, id);
	if(found!=null) {
		isDuplicateId=true;
	}
	if(isDuplicateId) {
		System.out.println("id đã tồn tại vui lòng nhập id mới");
		addNewPerson(newperson, list);
	}else {
	//Thêm name
	System.out.println("Nhập name:");
	String name=cs.nextLine();
	//Thêm birth of date
	System.out.println("Nhập birth of date:");
	String bod=cs.nextLine().toUpperCase();
	//Thêm nơi sinh
	System.out.println("Nhập nơi sinh:");
	String birthplace=cs.nextLine().toLowerCase();
	newperson=new Person(id, name, bod, birthplace);
	list.InsertPerson(newperson);
	}
}
public void inOrderTraversal(Node<Person> root,MyBSTree list) {
	list.inOrderTraversal(root);
}
public void postOrderTraversal(Node<Person>root,MyBSTree list) {
	list.postOrderTravesal(root);
}
public void prevOrderTraversal(Node<Person>root,MyBSTree list) {
	list.prevOrderTraversal(root);
}
public void BreadthFTraversal(MyBSTree list) {
	list.breadthFTraversal();
}
public void delete(MyBSTree list,String bcode) {
list.delete(bcode);
}
public void balance(MyBSTree list) {
    list.balance();
}
}
