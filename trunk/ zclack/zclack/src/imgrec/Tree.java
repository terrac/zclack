package imgrec;

import java.util.*;

/**
 * A tree is a data structure that holds values of type V.
 * Each tree has a single value of type V and can have any number of
 * branches, each of which is itself a Tree.
 */
public class Tree<V> {
    // The value of the tree is of type V.
    V value;

    // A Tree<V> can have branches, each of which is also a Tree<V>

    List<Tree<V>> branches = new ArrayList<Tree<V>>();

    // Here's the constructor.  Note the use of the type variable V.
    public Tree(V value) { this.value = value; }

    // These are instance methods for manipulating the node value and branches.
    // Note the use of the type variable V in the arguments or return types.
    V getValue() { return value; }
    void setValue(V value) { this.value = value; }
    int getNumBranches() { return branches.size(); }
    Tree<V> getBranch(int n) { return branches.get(n); }
    void addBranch(Tree<V> branch) { branches.add(branch); }
}