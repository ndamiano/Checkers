package edu.mccc.cos102.checkers;
import com.cbthinkx.util.Debug;
import edu.mccc.cos102.abstracts.AbstractAction;
@SuppressWarnings("rawtypes")
public class CheckersAction extends AbstractAction implements Comparable {
	private int from, to;
	public CheckersAction(int from, int to) {
		this.from = from;
		this.to = to;
	}
	public int getFrom() {
		return from;
	}
	public int getTo() {
		return to;
	}
    public int compareTo(Object o) {
        Debug.println("Being compared using compareTo");
        if (this.getFrom() < ((CheckersAction) o).getFrom()) {

        }
        return 0;
    }
    @Override
    public boolean equals(Object o) {
        Debug.println("Being compared using equals");
        if (this.getFrom() == ((CheckersAction) o).getFrom() && this.getTo() == ((CheckersAction) o).getTo()) {
            return true;
        } else {
            return false;
        }
    }
}
