package bgu.spl.mics.application.objects;

/**
 * Passive object representing a data used by a model.
 * Add fields and methods to this class as you see fit (including public methods and constructors).
 */

public class DataBatch {
    private Data data;
    private int start_index;
    private boolean proccesed;
    private int gpuIndex;
    private int ticks;


    public DataBatch(Data d, int start){
        data=d;
        start_index = start;
        this.proccesed = false;
        switch (data.getType()){
            case Text:
                ticks=2;
            case Images:
                ticks= 4;
            case Tabular:
                ticks= 1;
        }
    }

    public int getTicks() {
        return ticks;
    }

    public int getGpuIndex() {
        return gpuIndex;
    }

    public void setGpuIndex(int gpuIndex) {
        this.gpuIndex = gpuIndex;
    }

    public Data getData() { return data; }
    public boolean isProccesed() { return proccesed; }

    public int getStart_index() { return start_index; }
}
