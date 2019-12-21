package application;

public class Hint implements TheVisitor {

	@Override
	public String visit(HowManyMoved h) {
		// TODO Auto-generated method stub
		return " Observe How Many unique people moved ";
	}

	@Override
	public String visit(FillShapes f) {
		// TODO Auto-generated method stub
		return "Let the shapes inflate to their target size";
	}

	@Override
	public String visit(PayBill b) {
		// TODO Auto-generated method stub
		return "See what your friends owe and compute youre share of the bill";
	}

	@Override
	public String visit(Freeze f) {
		// TODO Auto-generated method stub
		return "Stop a moving object so it aligns with the guide";
	}

	@Override
	public String visit(Match m) {
		// TODO Auto-generated method stub
		return "Match colored words using their color or the color they spell ";
	}



	
}
