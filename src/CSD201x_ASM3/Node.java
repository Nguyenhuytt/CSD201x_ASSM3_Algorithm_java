package CSD201x_ASM3;

public class Node<T>{
 Person data;
 Node<T>personleft;
 Node<T>personright;
 boolean isDelete=false;
 
 public boolean isDelete() {
	return isDelete;
}
public void setDelete(boolean isDelete) {
	this.isDelete = isDelete;
}
public Node() {
	 
 }
//Insert new person:
public void insertNode(Person data) {
	if(data.compareTo(this.data)>0) {
		if(this.personright==null) {
			this.personright=new Node<T>(data);
		}else {
			this.personright.insertNode(data);
		
		}
	}else {
		if(this.personleft==null) {
			this.personleft=new Node<T>(data);
		}else {
			this.personleft.insertNode(data);
		}
	}
}
public Node(Person data) {
	this.data=data;
}
public Person getData() {
	return data;
}
public Node<T>getPersonleft() {
	return personleft;
}
public void setPersonleft(Node<T>personleft) {
	this.personleft = personleft;
}
public Node<T>getPersonright() {
	return personright;
}
public void setPersonright(Node<T>personright) {
	this.personright = personright;
}
@Override
public String toString() {
	return " "+this.data;
}
public void delete() {
	this.isDelete=true;
}
}
