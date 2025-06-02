import java.util.* ;

public class FileSystem {
    private final Node root ;
    private       Node cwd  ;
    // no necesito un stack, ya que guardo cada parent: en pwd puedo ir reconstruyendo para atras.

    public FileSystem() {
        root = new Node("root", null, true) ; // se crea sin nodo padre, ya que es el comienzo.
        cwd  = root ;
    }

    protected boolean create(String NodeName, boolean isDirectory){
        // creates a new node in the current directory
        // input:
        //          - name of the new node to be created
        //          - boolean to decide what type of node it is
        // output: true if it could be created, false if not.
        if(cwd.isDir()){
            Node newNode = new Node(NodeName, cwd, isDirectory) ;
            cwd.addChild(newNode);
            return true ;
        }
        else{
            return false ;
        }
    }

    protected Node resolvePath(String path){
        // resolves the specific case in which cd receives a whole path to change to.
        // input: path
        // output: last node of the path, or null in case of error.
        Node curr = path.startsWith("/") ? root : cwd ;
        String[] parts = path.split("/") ;
        for(String p : parts){
            if (p.isEmpty() || p.equals(".")) {continue; }
            if (!curr.isDir()) { return null ; }
            curr = curr.getChildren().get(p) ;
            if(curr == null){ return null ; }
        }
        return curr ;
    }

    public void cd(String path){
        // changes the current directory to the one specified with the arguments of the method.
        // input: path to go to.
        // output: exception in case of error. If not, nothing.
        if(path.equals("/")){
            cwd = root ;
            return;
        }
        if(path.equals("..")){
            if(cwd.getParent() != null){
                cwd = cwd.getParent() ;
            };
            return ;
        }
        Node curr = resolvePath(path) ;
        if(curr == null){
            throw new RuntimeException("Invalid path") ;
        }
        this.cwd = curr ;
    }

    public void touch(String fileName){
        // creates a new file in the current directory with a specific name required.
        // input: name of the new file
        // output: exception in case of error. If not, nothing
        boolean worked = this.create(fileName, false) ;
        if(!worked){
            throw new RuntimeException("This file could not be created") ;
        }
    }

    public void ls() {
        // lists all files and directories in the current directory.
        // input: -
        // output: prints all children of the current directory. If error, exception
        if (!cwd.isDir()){
            throw new RuntimeException("This location is not a directory") ;
        }
        List<String> names = new ArrayList<>(cwd.getChildren().keySet());
        Collections.sort(names);
        for (String n : names) {
            System.out.println(n + (cwd.getChildren().get(n).isDir() ? "/" : ""));
        }
    }

    public void mkdir(String dirName) {
        // creates a new directory in the current directory with a specific name required.
        // input: name of the new directory
        // output: exception in case of error. If not, nothing
        boolean worked = this.create(dirName, true);
        if (!worked) {
            throw new RuntimeException("This directory could not be created");
        }
    }

    public void pwd() {
        // prints out the whole path from root to the current directory.
        // input: -
        // output: prints in console the whole path to get to the current directory. If error, exception
        if (cwd == root) {
            System.out.println("/");
            return;
        }
        Deque<String> stack = new ArrayDeque<>();
        Node current = cwd;
        while (current != root) {
            stack.push(current.getName());
            current = current.getParent();
        }
        while (!stack.isEmpty()) {
            System.out.print("/" + stack.pop());
        }
        System.out.println();
    }

}
