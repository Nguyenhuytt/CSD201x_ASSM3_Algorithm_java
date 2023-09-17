package CSD201x_ASM3;

import java.util.ArrayList;
import java.util.List;

public class MyBSTree {
	Node root;

	public MyBSTree() {

	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	protected MyBSTree(Node root) {
		this.root = root;
	}

//Insert new person:
	public void InsertPerson(Person newperson) {
		if (root == null) {
			this.root = new Node(newperson);
		} else {
			root.insertNode(newperson);
		}
	}

//Prev-order-Traversal
	public void prevOrderTraversal(Node root) {
		if (root == null) {
			return;
		} else {
			System.out.println(root.toString());
			prevOrderTraversal(root.getPersonleft());
			prevOrderTraversal(root.getPersonright());
		}
	}

//In-Order-Traversal
	public void inOrderTraversal(Node root) {
		if (root == null) {
			return;
		} else {
			inOrderTraversal(root.getPersonleft());
			System.out.println(root.toString());
			inOrderTraversal(root.getPersonright());
		}
	}

//Post-order-Traversal
	public void postOrderTravesal(Node root) {
		if (root == null) {
			return;
		} else {
			postOrderTravesal(root.getPersonleft());
			postOrderTravesal(root.getPersonright());
			System.out.println(root.toString());
		}
	}

//Hàm kiểm tra tree rỗng
	public boolean isEmpty() {
		return root == null;
	}

	public void visit(Node<Person> p) {
		System.out.println(p.data);
	}

//Hàm Breadth-first-Traversal
	public void breadthFTraversal() {
		if (root == null)
			return;
		MyQueue q = new MyQueue();
		q.enqueue(root);
		while (!q.isEmpty()) {
			Node p = (Node) q.dequeue();
			System.out.println(p.data);
			if (p.personleft != null)
				q.enqueue(p.personleft);
			if (p.personright != null)
				q.enqueue(p.personright);
		}
	}

//Hàm tìm node
	public Node find(Node<Person> root, String x) {
		if (root == null) {
			return null;
		}
		if (root != null && root.data.getId().equals(x)) {
			return root;
		}
		if (root.personleft != null && root.data.getId().compareTo(x) > 0) {
			return find(root.personleft, x);
		} else {
			return find(root.personright, x);
		}

	}

//Hàm xóa node by ID
	public void delete(String code) {
		Node<Person> found = find(this.root, code);
		if (found == null) {
			System.out.println("không tìm thấy id nào phù hợp");
			return;
		} else {
			delete(this.root, code);
			System.out.println("Xóa nhân viên có mã id là " + code + " thành công");
		}
	}

//Hàm tìm id nhỏ nhất
	public Node<Person> minValueNode(Node<Person> node) {
		if (node.personleft != null) {
			return minValueNode(node.personleft);
		}
		return node;
	}

//Hàm delete node theo id
	public Node<Person> delete(Node<Person> root, String code) {
		if (root == null) { // Node không tồn tại trong cây
			return null;
		}
		if (code.compareTo(root.data.getId()) < 0) {// Nếu node ở bên trái
			root.personleft = delete(root.personleft, code);
		} else if (code.compareTo(root.data.getId()) > 0) {// Nếu node ở bên phải
			root.personright = delete(root.personright, code);
		} else {
			if (root.personleft == null && root.personright == null) {
				return null;
			} else if (root.personleft == null) {
				return root.personright;
			} else if (root.personright == null) {
				return root.personleft;
			} else {// Nếu node cần xóa có hai node con
				Node<Person> temp = minValueNode(root.personright);
				root.data = temp.data;// Thay đổi dữ liệu node cần xóa thành dữ liệu node con bên phải nhỏ nhất
				root.personright = delete(root.personright, temp.data.getId());// Xóa node nhỏ nhất
			}
		}
		return root;
	}

//Hàm xây dựng một mảng danh sách trong cây nhị phân
	private void buildArray(List<Node<Person>> list, Node<Person> p) {
		if (p == null) {
			return;
		}
		// Duyệt các node theo thứ tự trước và thêm vào list
		buildArray(list, p.personleft);
		list.add(p);
		buildArray(list, p.personright);
	}

	//Hàm cân bằng cây
	private void balance(List<Node<Person>> list, int firs, int last) {
		if (firs > last) {// Nếu f>l thì thoát phương thức
			return;
		}
		int mid = (firs + last) / 2;// Tính chỉ số giữa
		Node<Person> p = list.get(mid);// Lấy phần tử ở vị trí giữa ra khỏi danh sách
		InsertPerson(p.data);// Chèn p vào cây
		balance(list, firs, mid - 1);// Đệ quy để cân bằng nửa đầu danh sách
		balance(list, mid + 1, last);// Đệ quy để cân bằng nửa sau danh sách
	}

	public void balance() {
		List<Node<Person>> list = new ArrayList<Node<Person>>();
		buildArray(list, root);// Lưu tất cả các node của cây vào list
		balance(list, 0, list.size() - 1);// Cân bằng cây
		System.out.println("Tree is balanced!");
	}

}