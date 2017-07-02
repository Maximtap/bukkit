package com.Tigerwurm.Varo;

public class Hochsetzer implements Runnable {
	
	GameManager manager;
	
	public Hochsetzer(GameManager man) {
		this.manager = man;
	}
	
	@Override
	public void run() {
		manager.setzeTriesHoch();
		manager.getServer().getScheduler().runTaskLater(manager, this, (3600 * 24) * 25);
	}
}
