package main.java.com.jonfast565.sqlextractor;

import java.util.LinkedList;
import java.util.List;

public class SelectPortion {
    private List<SelectClause> clauseList = new LinkedList<SelectClause>();

    public List<SelectClause> getClauseList() {
        return clauseList;
    }

    public void setClauseList(List<SelectClause> clauseList) {
        this.clauseList = clauseList;
    }

    public void appendClauseList(SelectClause clause) {
        this.clauseList.add(clause);
    }
}
