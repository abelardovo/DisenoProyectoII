import java.util.Scanner;
import java.lang.reflect.Array;

class Graph {
	
	public int satDegree[];
	public Node nodes[];
	public MyList<Node> arcos[];

	/** Constructor */
    @SuppressWarnings("unchecked")
	public Graph (int nodes) {

		this.satDegree = new int[nodes];
		this.nodes = new Node[nodes];
		this.arcos = new MyList[nodes];
		
		for (int i=0; i<nodes; i++) {
		    this.arcos[i] = new MyList<Node>();
		}
	}

	public int findNode (int id) {
		for (int k=0; k<this.nodes.length;k++) {
			if (this.nodes[k].getId()==id)
				return k;
		}
		return -1;
	}
}

class Box<E> {
	
	private E element;
	private Box<E> next;
		
	/**
	 *  Crea una Box cuyo element es elem
	 */

	public Box () {
	}
	public Box(E elem) {
		
		this.element = elem;
        this.next = null;
	}

	/**
	 *  Retorna el element de la Box en cuestión.
	 */
	public E getElem() {
		
		return this.element;
	}
	
	/**
	 *  Retorna la Box next a la Box en cuestión.
	 */
	public Box<E> getNext() {
		
		return this.next;
	}
	
	/**
	 *  Permite modificar el element de una Box.
	 */
	public void modifyElement(E newElem) {
		
		this.element = newElem;
	}
	
	/**
	 *  Permite modificar el next Box de la Box en cuestión.
	 */
	public void modifyNext(Box<E> newNext) {
		
		this.next = newNext;
	}
}

class MyList<E>{
	
	private Box<E> first;
	private Box<E> last;
	private int size;

    /** Constructor */
    public MyList() {
        this.first = new Box<E>();
        this.first = null;
        this.last  = null;
    	this.size = 0;  
    }
	
    
    public void add(E element) {
    	
        Box<E> newBox = new Box<E>(element);
        
        if (this.size == 0) {         //Si la MyList es vacia, agregamos como first 
            this.first = newBox;
            this.first.modifyElement(element);
            this.first.modifyNext(null);
            this.last = this.first;     
        } else {                //Si la MyList no es vacia, agregamos al final
            newBox.modifyElement(element);
            this.last.modifyNext(newBox);
            this.last = newBox;
            this.last.modifyNext(null);
        }
        
        this.size++;
  
    }

    public Box<E> getfirst() {
    	
    	return this.first;
    }
   

    /**
     *  Retorna el element de la primera Box de la MyList.
     */
    public E get() {
    	
    	return this.first.getElem();
    }
    

    public boolean isEmpty() {
    	
    	return (this.size == 0);
    }

    public int getSize() {
    	
    	return this.size;  	
    }

    
    /**
     *  Retorna el element en la posición i de la MyList.
     */
    public E obtain(int i) {
    	
    	Box<E> aux = this.getfirst(); 
    	int contador =0;  
	
    	while ((contador<i) && (aux != null)) {
    		aux = aux.getNext();
    		contador ++;
    	}
	
    	return aux.getElem();
    }
    
    /**
     *  Imprime la MyList.
     */
    public void print(){
    	
		Box<E> aux;
		E data1;
		aux=this.first;
		
		while(aux!=null) {
			data1=aux.getElem();
			System.out.print(data1.toString()+" ");
			aux=aux.getNext();
		}
    }
    
    
    public String toString(){
    	
		Box<E> aux;
		E data1;
		aux=this.first;
		String res="";
		
		while(aux!=null) {
			data1=aux.getElem();
			res = res.concat(data1.toString() + " ");
			aux=aux.getNext();
		}
		return res;
    }    	
}

class Node {

	//id es unico
	public int color;
	public int id;
	public int degree;
	public boolean label;

	/**
	 * Crea un Node con id i.
	 */
	public Node (int i) {
		this.id = i;
		this.degree = 0;
		this.color = 0;
		this.label = false;
	}
	
	public int getColor() {
		return this.color;

	}

	public Node Clone(){

		Node aux = new Node(this.id);
		aux.setDegree(this.degree);
		aux.setColor(this.color);
		aux.setLabel(this.label);

		return aux;

	}

	public boolean getLabel () {
		return this.label;
	}

	public void setLabel (boolean l) {
		this.label = l;
	}

	public void setColor(int color) {
		this.color=color;
	}

	public int getId () {
		return this.id;
	}

	public int getDegree() {
	      return this.degree; 
	}

	public void setDegree (int c) {
	      this.degree = c; 
	}
	
    public boolean lessThan(Node node){
  	  
  	  return this.degree > node.degree;
    }

	@Override
	public String toString() {
        return Integer.toString(this.id);
	}
	


}

class Main {


    public void mix(Node[] nodes, int left, int middle, int right){  

        Node[] aux = new Node[right- left + 2]; // Arreglo auxiliar.
        int i= left; // Contador.
        int j= middle; // Contador.
        int k = 0; //Contador.

        while ((i != middle) && (j != right)){
        
            if (nodes[i].lessThan(nodes[j])){    

                aux[k]= nodes[i];
                i= i+1;
            } else {
                aux[k]= nodes[j];
                j= j+1;
            }

            k= k+1;
        }

        while (i != middle){
            aux[k]= nodes[i];
            i= i+1;
            k= k+1;
        }

        while (j != right){        
            aux[k]= nodes[j];
            j= j+1;
            k= k+1;
        }
        k=0;
            
        while (k<(right-left)){
            nodes[left+k]= aux[k];
            k= k+1;
        }
    }

    public void sort(Node[] nodes, int izq, int der){

        if (2<=(der-izq)) {        
            int med = (izq+der)/ 2;
            sort(nodes, izq, med);
            sort(nodes, med, der);
            mix(nodes, izq, med, der);
        }
  
    }

    public void mergesort(Node[] nodes, int n){
        sort(nodes, 0, n);
    }


	public int findMaxColor(Node[] nodes){
		int maxColor=0; 
		for (int i=0; i<nodes.length; i++) {

			if ((nodes[i] != null)&&(maxColor < nodes[i].getColor())) {
				maxColor=nodes[i].getColor();
			}

		}

		return maxColor;
	}

	public int possibleColor(Graph graph, int nextNode) {
		MyList<Node> arcs = graph.arcos[nextNode-1];
		int nextColor=1; 
		int i=0;
		while (i<arcs.getSize()) {
			Node aux = arcs.obtain(i);
			if (nextColor == graph.nodes[graph.findNode(aux.getId())].getColor()) {
				nextColor++;
				i=0;
			} else {
				i++;
			}
		

		}
		return nextColor;
	}

	public int getMax(Graph graph) {
		int max = 1,aux=0;
		Node[] nodes = graph.nodes;
		int[] satDeg = graph.satDegree;
		for (int i = 0; i < nodes.length; i++) {
			int aux1 = nodes[i].getId();
    		if (((satDeg[aux1-1] > aux) || ((satDeg[aux1-1]== aux) && 
    									   (nodes[graph.findNode(aux1)].getDegree() > nodes[graph.findNode(max)].getDegree())))
    		   && (nodes[graph.findNode(aux1)].getColor() == 0)) {
    			aux = satDeg[aux1-1];
    			max = aux1;
    		} 
      			
		}
		return max;
	}

	public int Dsatur(Graph graph) {
		int nextNode,color;
		int[] satDegree = new int[graph.nodes.length];

		graph.nodes[0].setColor(1);
		Node aux = graph.nodes[0];
		MyList<Node> arcs= graph.arcos[aux.getId()-1];
		for (int i=0; i<arcs.getSize(); i++) {
			Node aux1 = arcs.obtain(i);
			graph.satDegree[aux1.getId()-1]++;

		}


		for (int i=0; i<graph.nodes.length-1; i++) {
			nextNode=getMax(graph);
		//	aux = graph.nodes[nextNode-1];
			MyList<Node> arcs1= graph.arcos[nextNode-1];
			for (int j=0; j<arcs1.getSize(); j++) {
				Node aux1 = arcs1.obtain(j);
				graph.satDegree[aux1.getId()-1]++;
			}
			int nextColor=possibleColor(graph, nextNode);
			graph.nodes[graph.findNode(nextNode)].setColor(nextColor);
		}

		return findMaxColor(graph.nodes);


	}

	public int minRankByColor(MyList<Node> arcs, Graph graph, int color, int rank) {
		int rank1=rank;  
		for (int i=0; i<arcs.getSize(); i++) {
			Node aux = arcs.obtain(i);
			if (color == graph.nodes[graph.findNode(aux.getId())].getColor()) {
				if (aux.getId()<rank1) {
					rank1=aux.getId();
				}
			}
		}
		return rank1;


	}

	public boolean isAdj(Node element,MyList<Node> nodes) {
    	
    	boolean is = false;
    	Box<Node> aux = nodes.getfirst();
    	Node auxdato;
    	
    	while ((!(is)) && (aux != null)) {

    		auxdato = aux.getElem();
    		
    		if (auxdato.getId()==element.getId()) {
    			is = true;    			
    		}
    	    aux = aux.getNext();
    	}
    	return is;	
    }

	public void label(Graph graph, int node) {

		System.out.println("LABEL: "+node);
		MyList<Node> arcs= graph.arcos[node-1];
		for (int j=0; j<arcs.getSize(); j++) {
			Node aux = arcs.obtain(j);
			if (aux.getId() < node) {
				int color = graph.nodes[graph.findNode(aux.getId())].getColor();
				int minRank = minRankByColor(arcs,graph,color,aux.getId());
				graph.nodes[minRank-1].setLabel(true);
			}

		}
	}

	public int getMin (int a, int b) {
		if (a < b)
			return a;
		return b;
	}



	public int[] getPossibleColors  (int aux, Node[] partialSol, MyList<Node> colors) {
		int[] result = new int[colors.getSize()];


		for (int i =0; i<colors.getSize(); i++) {
			result[i]=i;
		}

		for (int k=0; k<partialSol.length; k++){
			if (!(partialSol[k]==null)) {
				Node aux1 = partialSol[k];
				if (isAdj(aux1,colors)) {

					int color = aux1.getColor();
					result[color]=-1;
				}
		
			}
		}
		return result;
	}

	public boolean notNull(int[] colors) {
		for (int i = 0; i<colors.length; i++) {
			if (!(colors[i]==-1)) 
				return true;
		}
		return false; 
	
	}

	public int minColor(int[] colors) {
		for (int i=0; i<colors.length; i++) {
			if (!(colors[i]==-1)) {
				return colors[i];
			}
		}
		return -1;
	}
	

	public int obtainMinRank(int color, Node[] nodes) {
		int rank=nodes.length+1;  
		for (int i=0; i<nodes.length; i++) {
			if ((color == nodes[i].getColor()) && (nodes[i].getId()<rank)) {
				rank=nodes[i].getId();
			}
		}
		return rank;

	}

	public int maxLabel(Node[] nodes) {


		int max = 0;
		for (int i = 0; i < nodes.length; i++) {
    		if ((nodes[i].getLabel()) && (nodes[i].getId()>max))
    			max = nodes[i].getId(); 		
		}
		return max;
	}

	public void brelaz(Graph graph, int colors) {
		
		int totalnodes = graph.nodes.length;
		int colorsUsed, aux;
		int q=colors;
		boolean back = false;

		Node a = graph.nodes[0].Clone();
		Node b = graph.arcos[graph.findNode(a.getId())].obtain(0).Clone();  
		int cliqueDim = 2; 
		int k = cliqueDim+1;
		int[] possibleColors = null;
		Node[] partialSol = new Node[graph.nodes.length];

		for (int p=0;p<partialSol.length;p++){
			partialSol[p] = null;
		}

		partialSol[a.getId()-1]=a;
		partialSol[b.getId()-1]=b;
		label(graph,a.getId());
		label(graph,b.getId());

		//while (!(colors==cliqueDim) && (k<=cliqueDim)) {
		while (true) {

			if (!back) {
				colorsUsed = findMaxColor(partialSol);
				aux = getMin(colorsUsed+1,colors-1);
				possibleColors = getPossibleColors(aux,partialSol,graph.arcos[k-1]);

			} else {
				int c = graph.nodes[graph.findNode(k)-1].getColor();
				possibleColors[c-1]=-1;
				graph.nodes[graph.findNode(k)-1].setLabel(false);
			}
			if (notNull(possibleColors)) {
				int color=minColor(possibleColors);

				int auxColor = graph.nodes[graph.findNode(k)].getColor();
				graph.nodes[graph.findNode(k)].setColor(color);
				
				System.out.println(k);
				k++;
				if (k>totalnodes) {
					if (!(auxColor==color)) {
						q--;
					}

					if (q==cliqueDim)
						break;

					for (int z=k; z<totalnodes; z++) {
						graph.nodes[graph.findNode(z)-1].setLabel(false);
					}
					back=true; 
				}

			} else {
				back=false;
			}
			if (back) {
				System.out.println(k);
				label(graph,k);
				k=maxLabel(graph.nodes);

				if (k<=cliqueDim)
					break;
			}

		} 

	}




	public static void main(String[] args) {
	 	Scanner in;
        Main main = new Main();
        String src,dst;
        long x = System.currentTimeMillis();
        in = new Scanner(System.in);
        
        String line = in.nextLine();
        String[] aux = line.split(" ");
        while (aux[0].equals("c")) {
        	line = in.nextLine();
        	 aux = line.split(" ");
        }

        String[] edge = new String[3];
        Graph graph = new Graph (Integer.parseInt(aux[2]));
        for (int i=0; i< Integer.parseInt(aux[2]); i++) {
        	MyList<Node> list = new MyList<Node>();
        	Node node = new Node(i+1);
        	graph.nodes[i] = node;
        	graph.arcos[i]= list;
        }

        for (int i =0; i<Integer.parseInt(aux[3]); i++ ) {
        	line = in.nextLine();
        	edge = line.split(" ");
        	src = edge[1];
        	dst = edge[2];
        	Node source = new Node(Integer.parseInt(src));
        	Node destination = new Node(Integer.parseInt(dst));
        	graph.nodes[Integer.parseInt(dst)-1].setDegree(graph.nodes[Integer.parseInt(dst)-1].getDegree() +1);
        	graph.nodes[Integer.parseInt(src)-1].setDegree(graph.nodes[Integer.parseInt(src)-1].getDegree() +1);
        	graph.arcos[Integer.parseInt(src)-1].add(destination);
        	graph.arcos[Integer.parseInt(dst)-1].add(source);
        }

        main.mergesort(graph.nodes,graph.nodes.length);
        int bound = main.Dsatur(graph);
       System.out.println("Color Antes: "+ main.findMaxColor(graph.nodes));

        main.brelaz(graph,bound);
       System.out.println("Color: "+ main.findMaxColor(graph.nodes));



       System.out.println("Time: "+((System.currentTimeMillis()-x)/1000));


        //int p =0;
    /* for (int k =0; k<graph.nodes.length; k++) {
    //     	//p=graph.Nodes[k].getDegree() + p;
    //   		System.out.println("Nodo "+(graph.nodes[k].getId())+" Degree "+graph.nodes[k].getDegree());
    // 	    System.out.println(k+1);
    //     	graph.arcos[k].print();
         	System.out.println();
         	System.out.println(graph.nodes[k].getId()+"   "+graph.nodes[k].getColor());

    //     
    //    //System.out.println(p);
     }*/
    }          
}


