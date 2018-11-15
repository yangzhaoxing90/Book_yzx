package Book;

public class PageUtil {
	
	public static final int pageSize = 3;
	
	public static int getTotalPage(int total){
		int totalPage = 1;
		if(total % pageSize == 0){
			totalPage = total/pageSize;
		}else{
			totalPage = total/pageSize  + 1;
		}
		return totalPage;
	}
}
