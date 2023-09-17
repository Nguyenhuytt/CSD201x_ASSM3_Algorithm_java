package CSD201x_ASM3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graph {
	int[][] matrix = new int[7][7];
	Scanner cs = new Scanner(System.in);

	// Chuyển String thành int,int đại diện cho String điểm
	public static int row(String str) {
		switch (str) {
		case "A":
			return 0;
		case "B":
			return 1;
		case "C":
			return 2;
		case "D":
			return 3;
		case "E":
			return 4;
		case "F":
			return 5;
		case "G":
			return 6;
		default:
			return -1;
		}
	}

	// Chuyển int thành String điểm
	public static String point(int i) {
		String string[] = { "A", "B", "C", "D", "E", "F", "G" };
		return string[i];
	}

	// Hàm đọc dữ liệu từ file
	public int[][] readFromFile(int marix[][]) {
		// Tạo đối tượng Scanner có tên là scan
		Scanner scan = null;
		try {
			// Truyền tham số cho đối tượng Scanner là đường dẫn tới file
			scan = new Scanner(Paths.get("D:\\CSD201x_ASM3\\graph.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i = 0;
		// đọc file theo dòng
		while (scan.hasNext()) {// Lặp lại đến khi không còn dòng nào trong tập tin được đọc
			String s = scan.nextLine();
			String string[] = s.split("\\s+");
			for (int j = 0; j < string.length; j++) {
				matrix[i][j] = Integer.parseInt(string[j]);
			}
			i++;
		}
		return matrix;

	}

	private static final int NO_PARENT = -1;

	public void dijkstra(int[][] adjacencyMatrix, int startVertex) {
		// khởi tạo số đỉnh trong đồ thị - nVertices - bằng độ dài của ma trận kề tính
		// từ cột đầu tiên của ma trận kề
		int nVertices = adjacencyMatrix[0].length;
		// Mảng lưu trữ khoảng cách ngắn nhất từ đỉnh bắt đầu đến đỉnh đang xét
		int[] shortestDistances = new int[nVertices];
		// Mảng kiểm tra xem một đỉnh đã được thêm vào cây đường đi ngắn nhất hay chưa.
		boolean[] added = new boolean[nVertices];
		// khởi tạo shortestDistances và added với giá trị tối đa và false tương ứng cho
		// đến khi nó được cập nhật trong quá trình thuật toán
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}
		shortestDistances[startVertex] = 0;// Điều chỉnh khoảng cách ngắn nhất của đỉnh bắt đầu thành 0.
		int[] parents = new int[nVertices];// Mảng lưu trữ đỉnh cha của mỗi đỉnh trong cây đường đi ngắn nhất
		// Đỉnh bắt đầu không có cha nên mảng cha của đó được đặt bằng giá trị
		// NO_PARENT.
		parents[startVertex] = NO_PARENT;
		for (int i = 1; i < nVertices; i++) {
			int nearestVertex = -1;// Biến này sẽ lưu giữ đỉnh gần nhất đến đỉnh hiện tại trong quá trình tìm đường
									// đi ngắn nhất.
			int shortestDistance = Integer.MAX_VALUE;// Biến lưu giữ khoảng cách ngắn nhất hiện tại từ đỉnh xuất phát
														// đến các đỉnh trong đồ thị.
			// Khởi tạo vòng lặp duyệt tất cả các đỉnh trong đồ thị
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				// Nếu đỉnh này chưa được thêm và khoảng cách từ đỉnh xuất phát đến đỉnh này là
				// ngắn nhất ta cập nhật giá trị
				// nearestVertex và shortestDistance với đỉnh đang xét
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
					nearestVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}
			added[nearestVertex] = true;// Đánh dấu đỉnh đã được thêm
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];// Khởi tạo cạnh đang xét bằng cách truy
																				// xuất phần tử tương ứng trong ma trận
																				// kề của đồ thị (adjacencyMatrix).
				// Nếu trọng số cạnh này >0 và và khoảng cách đi từ đỉnh xuất phát đến đỉnh
				// nearestVertex cộng thêm trọng số cạnh này nhỏ hơn khoảng cách từ đỉnh xuất
				// phát đến đỉnh đang xét hiện tại thì cập nhật giá trị của biến
				// shortestDistances[vertexIndex] và parents[vertexIndex].
				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
					parents[vertexIndex] = nearestVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}
		printSolution(startVertex, shortestDistances, parents);
	}

//Phương thức hiển thị khoảng cách và đường đi từ một đỉnh đến các đỉnh khác
	public void printSolution(int startVertex, int[] distances, int[] parents) {
		System.out.println("Matrix :");
		infomationmatrix(readFromFile(matrix));
		System.out.println("\nDijkstra");
		int nVertices = distances.length;// Khai báo biến bằng số lượng đỉnh trong đồ thị
		System.out.print("Vertex" + " |" + " Distance" + "|" + "Path");
		for (int vertexIndex = 4; vertexIndex < 5; vertexIndex++) {
			if (vertexIndex != startVertex) {
				System.out.print("\n" + point(startVertex) + " -> ");// In ra đỉnh ban đầu
				System.out.print(point(vertexIndex) + " |  ");// In ra đỉnh hiện tại đang xét
				System.out.print(distances[vertexIndex] + "  \t |");// In ra khoảng cách từ đỉnh ban đầu đến đỉnh hiện
																	// tại
				printPath(vertexIndex, parents);// In ra đường đi từ đỉnh ban đầu đến đỉnh hiện tại
			}
			System.out.println();
		}
	}

	// Phương thức in ra đường đi
	private static void printPath(int currentVertex, int[] parents) {

		if (currentVertex == NO_PARENT) {
			return;
		}
		printPath(parents[currentVertex], parents);
		System.out.print(point(currentVertex) + " ");
	}

	// Duyệt đồ thị theo chiều sâu
	public void DFS() {
		System.out.println("Matrix :");
		infomationmatrix(readFromFile(matrix));// Hiển thị thông tin trong file
		System.out.println("\nDFS_Graph");
		boolean[] visited = new boolean[ matrix.length];
		DFS(matrix, visited, 0);

	}
	//Hàm duyệt đồ thị theo chiều sâu
	public static void DFS(int[][] matrix, boolean[] visited, int node) {
		visited[node] = true;// Đánh dấu đỉnh đang xét đã được thăm
		System.out.print(point(node) + " ");
		for (int i = 0; i < matrix.length; i++) {// Lặp qua tất cả các đỉnh trong matrix
			if (matrix[node][i] != 0 && matrix[node][i] != 9999 && !visited[i]) {
				DFS(matrix, visited, i);
			}
		}
	}

	// Hàm in mảng matrix
	public void infomationmatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

	}
	//Hàm duyệt đồ thị theo chiều rộng
	public void BFS() {
		int[][] matrix1 = readFromFile(matrix);
		BFS(matrix1, 0);
	}
	//Hàm duyệt đồ thị theo chiều rộng
	public void BFS(int[][] matrix, int start) {
		MyQueue queue = new MyQueue();
		//Tạo mảng boolean visited để kiểm tra các điểm đã được duyệt qua chưa
		boolean[] visited = new boolean[matrix.length];
		visited[start] = true;//Duyệt đỉnh đầu tiên
		queue.enqueue(start);//Thêm đỉnh vào hàng đợi
		while (!queue.isEmpty()) {
			int cur = (int) queue.peek();//Lấy các đỉnh ra khỏi hàng đợi
			queue.dequeue();//Xóa đỉnh đầu hàng đợi
			System.out.print(point(cur) + " ");//Hiển thị các đỉnh
			//Lặp qua tất cả các đỉnh kề của đỉnh hiện tại và thêm chúng vào hàng đợi nếu chúng chưa được duyệt qua
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[cur][i] != 0&&matrix[cur][i]!=9999&& !visited[i]) {
					visited[i] = true;
					queue.enqueue(i);
				}
			}
		}
	}
}
