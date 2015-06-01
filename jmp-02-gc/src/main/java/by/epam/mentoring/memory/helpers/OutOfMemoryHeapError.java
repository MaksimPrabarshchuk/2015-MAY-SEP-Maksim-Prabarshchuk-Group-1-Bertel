package by.epam.mentoring.memory.helpers;

import by.epam.mentoring.memory.processor.IProcessor;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryHeapError implements IProcessor {
    //Inside ArrayList we have  'Object[] elementData' array
    List<Object> list = new ArrayList<Object>();


    @Override
    public void process() {
        while (true) {
            list.add(new Object());
        }
    }
}
