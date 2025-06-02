import java.util.* ;

public class Node {
    private final boolean           isDir    ;
    private       String            name     ;
    private final Map<String, Node> children ;
    private       Node              parent   ;

    public Node(String name, Node parent, boolean isDir){
        // creates a node by assigning its attributes
        this.name   = name ;
        this.isDir  = isDir ;
        this.parent = parent ;

        // depending on the nodes type, it will or will not have children
        if (isDir){
            this.children = new HashMap<>() ;
        }
        else{
            this.children = null ;
        }
    }

    public Node getParent(){
        // returns de parent node of this node
        return parent ;
    }
    public String getName(){
        // returns the name of this node
        return name ;
    }
    public boolean isDir(){
        // returns if this Node is file or directory
        return isDir ;
    }

    public Map<String, Node> getChildren() {
        // if this nodes type is a directory, it returns its children
        if (!isDir) {
            throw new IllegalStateException("This is not a directory");
        }
        return children;
    }

    public void addChild(Node n) {
        // adds a node to its children list, even if it´s another directory or file.

        // checks if its directory
        if(!isDir()){
            throw new RuntimeException("This is not a directory") ;
        }

        // checks if the file already exists
        String nName = n.getName() ;
        if(getChildren().containsKey(nName)){
            throw new RuntimeException("This file already exists in this directory") ;
        }

        // add file to directory
        getChildren().put(nName, n) ;
    }
}
