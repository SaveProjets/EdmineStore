package fr.edminecoreteam.store.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.store.Main;
import fr.edminecoreteam.store.data.StoreData;

public class ShopOfTheWeek extends BukkitRunnable
{
	public int timer;
    @SuppressWarnings("unused")
	private Main main;
    private ShopOfTheWeekData data;
    
    
    public ShopOfTheWeek(Main main) {
        this.main = main;
        this.timer = 15;
        this.data = new ShopOfTheWeekData();
    }
    
	public void run() {
        if (timer == 0) {
            if (data.hisReductionExist())
            {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);  
                Date resultdate = new Date();
                String date = sdf.format(resultdate);
                Calendar calendar = Calendar.getInstance();
                Calendar futureCalendar = (Calendar) calendar.clone(); // Créer une copie de l'objet Calendar
                futureCalendar.add(Calendar.DAY_OF_YEAR, 7); // Ajouter 7 jours à la copie
                Date resultDate = futureCalendar.getTime();
                String dateDeadLine = sdf.format(resultDate);
            	if (data.getDeadLine().equalsIgnoreCase(date))
            	{
            		for (int i : data.getAllArticleIds())
            		{
            			StoreData sData = new StoreData(i);
            			sData.updateReductionPercentage(0);
            			sData.updateIsInReduction("disable");
            		}
            		data.deleteAllArticles();
            		for (int i : data.getRandomArticles())
            		{
            			ShopOfTheWeekData createData = new ShopOfTheWeekData(i, date, dateDeadLine);
            			StoreData sData = new StoreData(i);
            			sData.updateReductionPercentage(getRandomNumber());
            			sData.updateIsInReduction("enable");
            			createData.createArticle();
            		}
            	}
            }
            else if (!data.hisReductionExist())
            {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);  
                Date resultdate = new Date();
                String date = sdf.format(resultdate);
                Calendar calendar = Calendar.getInstance();
                Calendar futureCalendar = (Calendar) calendar.clone(); // Créer une copie de l'objet Calendar
                futureCalendar.add(Calendar.DAY_OF_YEAR, 7); // Ajouter 7 jours à la copie
                Date resultDate = futureCalendar.getTime();
                String dateDeadLine = sdf.format(resultDate);
            	for (int i : data.getRandomArticles())
            	{
            		ShopOfTheWeekData createData = new ShopOfTheWeekData(i, date, dateDeadLine);
            		StoreData sData = new StoreData(i);
        			sData.updateReductionPercentage(getRandomNumber());
        			sData.updateIsInReduction("enable");
            		createData.createArticle();
            	}
            }
        	
            timer = 500;
        }
        --timer;
    }
	
	public int getRandomNumber() {
	    Random rand = new Random();
	    int randomNumber = rand.nextInt(16) + 5; // 16 car il y a 16 nombres entre 5 et 20 inclus
	    return randomNumber;
	}
}
