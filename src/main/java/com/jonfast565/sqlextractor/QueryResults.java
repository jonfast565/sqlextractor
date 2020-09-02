package main.java.com.jonfast565.sqlextractor;

public class QueryResults {
    private JoinPortion joinPortion;
    private SelectPortion selectPortion;

    public QueryResults(JoinPortion joinPortion, SelectPortion selectPortion) {
        this.joinPortion = joinPortion;
        this.selectPortion = selectPortion;
    }

    public JoinPortion getJoinPortion() {
        return joinPortion;
    }

    public void setJoinPortion(JoinPortion joinPortion) {
        this.joinPortion = joinPortion;
    }

    public SelectPortion getSelectPortion() {
        return selectPortion;
    }

    public void setSelectPortion(SelectPortion selectPortion) {
        this.selectPortion = selectPortion;
    }
}
