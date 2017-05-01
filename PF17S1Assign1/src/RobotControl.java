
// Use the method r.up(), r.down(), 
//r.extend(), r.contract(), 
//r.raise(), r.lower(), 
//r.pick(), r.drop()

class RobotControl {
	private Robot r;

	public RobotControl(Robot r) {
		this.r = r;
	}

	public void control(int barHeights[], int blockHeights[], int required[], boolean ordered) {
		// calling functions
		PartAClass.PartA(r);
		//PartBClass.PartB(barHeights,r);
		//PartCClass.PartC(barHeights, blockHeights, r);
		//PartDClass.PartD(barHeights, blockHeights, required, r);
		//PartEClass.PartE(barHeights, blockHeights,r);
	}
}