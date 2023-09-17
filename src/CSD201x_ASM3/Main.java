package CSD201x_ASM3;

import java.util.List;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner cs = new Scanner(System.in);
		personManagement PM = new personManagement();
		MyBSTree list = new MyBSTree();
		Person newperson = new Person();
		Node newnode=new Node();
		Graph graph=new Graph();
		boolean stop = false;
		do {
			String control = displayMenu(cs);
			switch (control) {
			case "1"://Thêm người mới vào BST
				PM.addNewPerson(newperson, list);
				break;
			case "2":
				//Prev-Order-Traversal
				System.out.println("Prev-Order-Traversal:");
				PM.prevOrderTraversal(list.root, list);
				//In-Order-Traversal
				System.out.println("In-Order-Traversal:");
				PM.inOrderTraversal(list.root, list);
				//Post-Order-Traversal
				System.out.println("Post-Order-Traversal:");
				PM.postOrderTraversal(list.root, list);
				break;
			case "3"://Duyệt cây theo chiều rộng
				PM.BreadthFTraversal(list);
				break;
			case "4"://Tìm kiếm thông tin nhân viên theo id
				System.out.println("Nhập id: ");
				String id=cs.nextLine();
				if(list.find(list.root, id)==null) {
					System.out.println("id không tồn tại vui lòng chọn lại.");
				}else {
				System.out.println(list.find(list.root,id));
				}
				break;
			case "5"://Xóa nhân viên theo id
				System.out.println("Nhập id cần xóa: ");
				String bcode=cs.nextLine();
				PM.delete(list,bcode);
				break;
			case "6"://Cân bằng cây
				PM.balance(list);
				break;
			case "7":
				graph.DFS();//Duyệt đồ thị theo chiều sâu
				System.out.println();
				System.out.println("BFS_Graph:");//Duyệt đồ thị theo chiều rộng
				graph.BFS();
				System.out.println();
				System.out.println("------------------------------------------");
				break;
			case "8"://Tìm đường đi ngắn nhất bằng thuật toán Dijkstra
				graph.dijkstra(graph.readFromFile(graph.matrix), graph.matrix[0][0]);
				System.out.println("------------------------------------------");
				break;
			case "0":// Kết thúc trương trình
				stop = true;
				System.out.println("Exit");
				break;
			default:
				try {
					Integer.parseInt(control);
					System.out.println("Số nguyên bạn nhập phải trong khoảng 0->8");
				} catch (Exception e) {
					System.out.println("Bạn cần nhập một số nguyên 0->8 theo từng chức năng");
				}
				break;
			}
		} while (!stop);
	}

	public static String displayMenu(Scanner cs) {
		System.out.println("Choose one of this options:");
		System.out.println("Person Tree:");
		System.out.println("1.Insert a new Person.");
		System.out.println("2.Prevorder Traversal/Inorder Traversal/Postorder Traversal.");
		System.out.println("3.Breadth-First Traversal.");
		System.out.println("4.Search By Person ID.");
		System.out.println("5.Delete By Person ID.");
		System.out.println("6.Balancing Binary Search Tree.");
		System.out.println("7.DFS-Graph/BFS_Graph.");
		System.out.println("8.Dijkstra.");
		System.out.println("0.Exit.");
		String control = cs.nextLine();
		return control;
	}
}
