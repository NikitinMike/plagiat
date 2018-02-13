package binarytreesample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
        
public class BinaryTree {
        
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("BinaryTreePU");
    static EntityManager em = factory.createEntityManager();

    BinaryTree.Node root;
    int count=0;

//    BinaryTree() {root=new Node(0, "");}
//    BinaryTree(long key, String name) {root=new Node(key, name);}
    
    class Node {
        long id;
        int cnt;
        String name;
        Node left,right;
        
        Node(long id, String name) {
            cnt=1;
            this.id = id;
            this.name = name;
        }
        
        public String toString() {
            return name+"("+id+")";
//            return name + ":" + cnt;
//            return name + " has the key " + key;            
//            return name+" has the key "+key+" Left:"+left+" Right:"+right+"\n";
        }
        
        int compareTo(Node a){
            return cnt-a.cnt;
        }
    }
    
    public void list(ArrayList<Node> list, Node focusNode) {
        if (focusNode == null) return ;
        list(list,focusNode.left);
        list.add(focusNode);
        list(list,focusNode.right);
    }

    public Node addNode(long id, String name) {
        if (root == null) return root = new Node(id,name);
        Node parent=root;
        int test;
        count++;
        do {
            test=name.compareTo(parent.name);
            if (test<0) 
                if (parent.left != null) parent=parent.left;
                else return parent.left = new Node(id,name);
            else if (test>0) 
                if (parent.right != null) parent=parent.right;
                else return parent.right = new Node(id,name);
        } while (test!=0);
        parent.cnt++;
        return parent;
    }

    public void inOrderTraverseTree(Node focusNode) {
        if (focusNode == null) return;
        inOrderTraverseTree(focusNode.left);
        System.out.println(focusNode);
        inOrderTraverseTree(focusNode.right);
    }

    public void preorderTraverseTree(Node focusNode) {
        if (focusNode == null) return;
        System.out.println(focusNode);
        preorderTraverseTree(focusNode.left);
        preorderTraverseTree(focusNode.right);
    }

    public void postOrderTraverseTree(Node focusNode) {
        if (focusNode == null) return;
        postOrderTraverseTree(focusNode.left);
        postOrderTraverseTree(focusNode.right);
        System.out.println(focusNode);
    }

    public Node findNode(String s) {
        Node focusNode = root;
        int t;
        do {
            if (focusNode == null) return null;
            t = s.compareToIgnoreCase(focusNode.name);
            if (t>0) focusNode = focusNode.right;
            if (t<0) focusNode = focusNode.left;
        } while (t!=0);
        return focusNode;
    }
    
    public static Link link;

    public static void main(String[] args) throws Exception {
        
        link = new Link();        // URL
        String URL="https://ru.wikipedia.org/wiki/%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F:%D0%A1%D0%BB%D1%83%D1%87%D0%B0%D0%B9%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0";
        String site = getHTML(URL);
        
        BinaryTree theTree = new BinaryTree();
        List<Word> words = em.createQuery("select w from Word w").getResultList();
//        System.out.println("WordsList:"+words.toString());
//        System.out.println("Words:"+words.size());
        for (Word word : words) if(theTree.addNode(word.getId(),word.getWord())!=null); // System.out.print(word);        
        System.out.println("Tesaurus: "+theTree.count); // words        
        
        Long wc=0L;
        for (String w:site.replaceAll("[^а-яёА-ЯЁ]"," ").split("\\s+")) // \\p{Alpha}
            if (w.length()>2) {
                wc++;
//                System.out.print(wc+":");
                Node n = theTree.findNode(w);
                if (n==null) {
//                    System.out.print("+");
                    em.getTransaction().begin();
                    Word word = new Word(w.toLowerCase(),wc);
                    em.persist(word);
                    em.getTransaction().commit();
//                    System.out.print("~"+word);
                    theTree.addNode(word.getId(),word.getWord());
//                    em.getTransaction().begin();
                    em.persist(new Article(link.getId(),word.getId(),wc));
//                    em.getTransaction().commit();
                } else {
//                    System.out.print(n);
//                    em.getTransaction().begin();
                    em.persist(new Article(link.getId(),n.id,wc));
//                    em.getTransaction().commit();
                }
//                System.out.print(", ");                
            }
        System.out.println("WordsCount: "+wc);
        
//        System.out.println("inOrderTraverseTree");
//        theTree.inOrderTraverseTree(theTree.root);
//        System.out.println("");
        
//        System.out.println("preorderTraverseTree");
//        theTree.preorderTraverseTree(theTree.root);
//        System.out.println("");
        
//        System.out.println("postOrderTraverseTree");
//        theTree.postOrderTraverseTree(theTree.root);
//        System.out.println("");
         
        // Find the node with key 75
//        System.out.println("\nNode with the key 75");
//        System.out.println(theTree.findNode(75));

//        ArrayList<Node> list = new ArrayList<>();
//        System.out.println("ListSize: " + list.size());
//        list.sort((a,b)->b.cnt-a.cnt);
//        theTree.list(list,theTree.root);

        System.out.println("AllWords: "+theTree.count);
        
//    Comparator<Node> comp = (Node a, Node b) -> {return b.compareTo(a);};
//        list.sort((Node a,Node b)-> b.cnt-a.cnt);
//        list.sort((Node a,Node b)-> {return b.cnt-a.cnt;});
//        list.sort((Node a,Node b)-> {return b.compareTo(a);});
//        System.out.println(list);
        
//        System.out.println(article.toString());
//        System.out.println(word.toString());

        em.close();        
        
    }
        

   public static String getHTML(String urlToRead) throws Exception {
      StringBuilder result = new StringBuilder();      
      URL url = new URL(urlToRead);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();                   
      InputStream is = conn.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      //
        em.getTransaction().begin();
        link.setName(URLDecoder.decode(conn.getURL().toString(),"UTF-8"));
        em.persist(link);
        em.getTransaction().commit();
        System.out.println("WordsList:"+link.toString());
      //  
      for(String line;(line=rd.readLine())!=null;) result.append(line);
      rd.close();
      return result.toString();
   }
    
}