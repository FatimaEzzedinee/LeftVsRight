package application;

public interface TheVisitor {

 public String visit(HowManyMoved h);	
 public String visit(FillShapes f);	
 public String visit(PayBill b);	
 public String visit(Freeze f);
 public String visit(Match m);	
 
 
}
