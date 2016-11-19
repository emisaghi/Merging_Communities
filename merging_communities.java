import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt(); // Number of people
        int q = stdin.nextInt(); // Number of queries
        stdin.nextLine();
		Person[] persons = new Person[n];
        for (int i=0;i<n;i++)
            persons[i] = new Person();
        int p1, p2;
        for(int i=0;i<q;i++){
            String s = stdin.nextLine();
            String[] parts = s.split(" "); // Split at an empty space
            String ch = parts[0]; // M or Q
            p1 = Integer.parseInt(parts[1])-1; // First Number
            if(ch.equals("M")){
                p2=Integer.parseInt(parts[2])-1; // Second Number
				int root1 = findRoot(persons, p1);
				int root2 = findRoot(persons, p2);
				if (root1 != root2) {
					persons[root2].parent = root1;
					persons[root1].size += persons[root2].size;
                }
                //System.out.println(Arrays.deepToString(c)); // To test the array
            }
            else if(ch.equals("Q"))
				System.out.println(persons[findRoot(persons, p1)].size);
            else
                System.out.println("Error!");
        }
        
    }
    static int findRoot(Person[] persons, int index) {
		int root = index;
		while (persons[root].parent != -1) {
			root = persons[root].parent;
		}

		int p = index;
		while (p != root) {
			int nextP = persons[p].parent;
			persons[p].parent = root;
			p = nextP;
		}

		return root;
	}
}


class Person {
	int parent = -1;
	int size = 1;
}
