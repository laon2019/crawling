package EPLcrawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EPL_Test {
	List<EPL> list = new ArrayList<>();

	public static void main(String[] args) {
		EPL_Test st = new EPL_Test();
		st.EPL();
		
		
	}

	private void EPL() {
		EPL_DAO dao = EPL_DAO.newInstance();
			String url = "https://www.premierleague.com/tables";
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch(Exception e) {
				System.out.println(e);
			}
			
			for(int i=0;i<39;i+=2) {
//			System.out.println(doc);
			
			Element club = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(2) span.long").get(0);
			System.out.println(club.text());
			Element played =  doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(3)").get(0);
			System.out.println(played.text());
			Element won = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(4)").get(0);
			System.out.println(won.text());
			Element drawn = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(5)").get(0);
			System.out.println(drawn.text());
			Element lost = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(6)").get(0);
			System.out.println(lost.text());
			Element gf = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(7)").get(0);
			System.out.println(gf.text());
			Element ga = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(8)").get(0);
			System.out.println(ga.text());
			Element gd = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(9)").get(0);
			System.out.println(gd.text());
			Element points = doc.select("tbody.tableBodyContainer tr:eq(" + i + ") td:eq(10)").get(0);
			System.out.println(points.text());

			
				
	        System.out.println("----------------------------------------------------------------");
				
				EPL vo = new EPL();
				vo.setClub(club.text());
				vo.setPlayed(played.text());
				vo.setPoints(points.text());
				vo.setWon(won.text());
				vo.setDrawn(drawn.text());
				vo.setLost(lost.text());
				vo.setGf(gf.text());
				vo.setGa(ga.text());
				vo.setGd(gd.text());
				
				
				dao.EPLInsert(vo);
				
				
		}
		
		
	}
}
