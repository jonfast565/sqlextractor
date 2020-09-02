package main.java.com.jonfast565.sqlextractor;

import java.util.LinkedList;
import java.util.List;

public class JoinPortion {
    private List<JoinClause> clauseList = new LinkedList<JoinClause>();

    public List<JoinClause> getClauseList() {
        return clauseList;
    }

    public void setClauseList(List<JoinClause> clauseList) {
        this.clauseList = clauseList;
    }

    public void appendClauseList(JoinClause clause) {
        this.clauseList.add(clause);
    }
}
