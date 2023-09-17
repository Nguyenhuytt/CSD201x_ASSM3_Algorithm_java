package CSD201x_ASM3;

public class Person implements Comparable<Person>{
 String id;
 String name;
 String bod;
 String birthplace;
public Person() {
	
}
protected Person(String id, String name, String bod, String birthplace) {
	this.id = id;
	this.name = name;
	this.bod = bod;
	this.birthplace = birthplace;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getBod() {
	return bod;
}
public void setBod(String bod) {
	this.bod = bod;
}
public String getBirthplace() {
	return birthplace;
}
public void setBirthplace(String birthplace) {
	this.birthplace = birthplace;
}
@Override
public String toString() {
	return String.format("%-3s %-15s %-15s %-15s %n", id,name,bod,birthplace );
}
@Override
public int compareTo(Person o) {
	return this.id.compareTo(o.id);
}
public int compareTo(String code) {
	// TODO Auto-generated method stub
	return this.id.compareTo(code);
}

}