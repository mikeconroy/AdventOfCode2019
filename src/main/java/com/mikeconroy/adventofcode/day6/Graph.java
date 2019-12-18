package com.mikeconroy.adventofcode.day6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    private Map<String, Node> nodes;

    public Graph(){
        nodes = new HashMap<String, Node>();       
    }

    public void put(String key, String value){
        Node parentNode = getOrCreateNode(key);
        Node childNode = getOrCreateNode(value);
        parentNode.add(childNode);
    }

    public int countDirectOrbits(){
        int sum = 0;
        for(String key : nodes.keySet()){
            sum += nodes.get(key).getNoOfConnectedNodes();
        }
        return sum;
    }

    public int countTotalOrbits(){
        int sum = 0;
        for(String key : nodes.keySet()){ 
            sum += nodes.get(key).getNoOfParentsToRoot();
        }

        return sum;

    }

    private Node getOrCreateNode(String nodeName){
        if(!nodes.containsKey(nodeName)){
            nodes.put(nodeName, new Node(nodeName));
        }
        return nodes.get(nodeName);
    }

    private class Node{
        private String name;
        private Node parentNode;
        private Set<Node> childNodes = new HashSet<>();

        public Node(String name){
            this.name = name;
        }

        public int getNoOfParentsToRoot(){
            if(parentNode == null){
                return 0;
            } else {
                return 1 + parentNode.getNoOfParentsToRoot();
            }
        }

        public void add(Node node){
            childNodes.add(node);
            node.setParentNode(this);
        }

        public void setParentNode(Node parentNode){
            this.parentNode = parentNode;
        }

        public int getNoOfConnectedNodes(){
            return childNodes.size();
        }

        @Override
        public String toString(){
            if (childNodes.isEmpty()){
                return name + "]";
            } else {
                return name + ", " + childNodes;
            }
        }
    }

    @Override
    public String toString(){
        return nodes.get("COM").toString();
    }

}