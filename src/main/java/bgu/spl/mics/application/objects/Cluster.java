package bgu.spl.mics.application.objects;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Passive object representing the cluster.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * Add all the fields described in the assignment as private fields.
 * Add fields and methods to this class as you see fit (including public methods and constructors).
 */
public class Cluster {
	private List<CPU> cpu;
	private List<GPU> gpu;
	private Queue<DataBatch> endProcessing;
	private Queue<DataBatch> unProcess;
	private static Cluster INSTANCE= Cluster.getInstance();
	private statistics statistics;



	/**
	 * Retrieves the single instance of this class.
	 */
	public static Cluster getInstance() {
		//TODO: Implement this
		return new Cluster();
	}
	public Cluster(){
		endProcessing = new LinkedBlockingDeque<>();
		unProcess = new LinkedBlockingDeque<>();
		cpu = new LinkedList<>();
		gpu = new LinkedList<>();
		statistics = new statistics();
	}

	public void addUnProcessed(DataBatch batch) {
		unProcess.add(batch);
	}
	public boolean full(){
		for (CPU c : cpu){
			if (!c.isProcessing())
				return false;
		}
		return true;
	}

	public void addProcessedData(DataBatch d) {
	}
	public statistics getStatistics(){
		return statistics;
	}

	public void setGpu(List<GPU> gpu) {
		this.gpu = gpu;
	}

	public void setCpu(LinkedList<CPU> cpu) {
		this.cpu = cpu;
	}

}
