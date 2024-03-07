package org.ksolves;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import java.util.Map;

public class Bolt extends BaseRichBolt {
    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        int number = tuple.getInteger(0);
        if(isPrime(number)){
            System.out.println(number);
        }
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("Number"));
    }

    private boolean isPrime(int n){
        if(n==1 || n==2 || n==3){
            return true;
        }
        if(n%2 ==0){
            return false;
        }
        for (int i =3;i*i<n;i+=2){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}
