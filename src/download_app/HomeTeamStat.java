package download_app;




public class HomeTeamStat extends TeamStat {

	public HomeTeamStat(int win, int draw, int loss, int goal_for,
			int goal_against) {
		super(win, draw, loss, goal_for, goal_against);
		// TODO Auto-generated constructor stub
	}

	@Override
	void computeStat() {
		
		for(int i=0;i<resultList.size();i++){
			int R = resultList.get(i).getHome_team_score()- resultList.get(i).getAway_team_score();
		if(R>0){
			setWin(getWin()+1);
		}
		if(R==0){
			setDraw(getDraw()+1);
		}
		if(R<0){
			setLoss(getLoss()+1);
		}
		
		setGoal_for(getGoal_for() + resultList.get(i).getHome_team_score());
		setGoal_against(getGoal_against()+ resultList.get(i).getAway_team_score());
		}
		
		setPoint_average(((float)getWin()*3 + (float)getDraw())/(getWin()+getDraw()+getLoss()));
		
	}

}
