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

    public int getOrbitalTransfersRequired(String from, String to){
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        HashMap<Node, Integer> fromParentsAndCountsMap = new HashMap<>();
        fromNode.getParentsAndCounts(fromParentsAndCountsMap, 0);

        HashMap<Node, Integer> toParentsAndCountsMap = new HashMap<>();
        toNode.getParentsAndCounts(toParentsAndCountsMap, 0);

        int least = Integer.MAX_VALUE;
        for(Node key : fromParentsAndCountsMap.keySet()){
            if(toParentsAndCountsMap.containsKey(key)){
                int fromCount = fromParentsAndCountsMap.get(key);
                int toCount = toParentsAndCountsMap.get(key);
                int sum = fromCount + toCount;
                if(sum < least){
                    least = sum;
                }
            }
        }

        return least - 2;
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

        public int getParentsAndCounts(Map<Node, Integer> parentsAndCountsMap, int count){
            if(parentNode == null){
                return 0;
            } else {
                count++;
                parentsAndCountsMap.put(parentNode, count);
                return 1 + parentNode.getParentsAndCounts(parentsAndCountsMap, count);
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