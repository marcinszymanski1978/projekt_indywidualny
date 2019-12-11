package controller;

import hibernate.HibernateDao;
import monitoredElements.Glucose;
import monitoredElements.Ingredients;
import monitoredElements.Meals;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GlucoseController {
    private List<Glucose> glucoseList;
    private List<Meals> mealsList;
    private List<Ingredients> ingredientsList;
    private HibernateDao glucoseDao;


    public GlucoseController() {
        glucoseDao = new HibernateDao();
        try{
        glucoseList = glucoseDao.getGlucoseLevels();
            }
        catch (NullPointerException e){
            e.getMessage();
        }

    }

    @RequestMapping("/glucoseForm")
    public ModelAndView showform(){
        return new ModelAndView("glucoseForm","command", new Glucose());
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("GlucoseLevelSave") Glucose glucoseMeasurement){



        if(glucoseMeasurement.getId()!=null) {
            //update


            if (!(glucoseDao.updateHibernateEntity(glucoseMeasurement).equals("COMMITTED"))) {

                System.out.println("Update measurement nr: " + getGlucoseMeasurementById(glucoseMeasurement.getId()));
                getGlucoseMeasurementById(glucoseMeasurement.getId()).setDate(glucoseMeasurement.getDate());
                getGlucoseMeasurementById(glucoseMeasurement.getId()).setGlucose(glucoseMeasurement.getGlucose());
                getGlucoseMeasurementById(glucoseMeasurement.getId()).setMesurmentStates(glucoseMeasurement.getMesurmentStates());
            }
            glucoseDao.updateHibernateEntity(glucoseMeasurement);

            
        }
         else {

            //add new
            if (!(glucoseDao.saveHibernateEntity(glucoseMeasurement).equals("COMMITTED"))) {
            System.out.println(glucoseMeasurement.getDate()
                    + " " + glucoseMeasurement.getGlucose()
                    + " " + glucoseMeasurement.getMesurmentStates()
                    +" was saved");
            System.out.println("New glucose measurement");
            glucoseMeasurement.setId(glucoseList.size() + 1);
            glucoseList.add(glucoseMeasurement);}
            glucoseDao.saveHibernateEntity(glucoseMeasurement);


        }

        return new ModelAndView("redirect:/viewGlucoseList");
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ModelAndView delete(@RequestParam String id){

        Glucose glucose_to_delete = getGlucoseMeasurementById(Integer.parseInt(id));

        if (!(glucoseDao.deleteHibernateEntity(glucose_to_delete).equals("COMMITTED"))) {

        glucoseList.remove(glucose_to_delete);

        }


        System.out.println("Delete "+ glucose_to_delete );


        return new ModelAndView("redirect:/viewGlucoseList");
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView edit(@RequestParam String id){
        Glucose glucose_update = getGlucoseMeasurementById(Integer.parseInt(id));
        return new ModelAndView("glucoseForm","command", glucose_update);
    }

    @RequestMapping(value="/refresh", method=RequestMethod.POST)
    public ModelAndView refresh(){
        System.out.println("Refresh");
        return new ModelAndView("redirect:/viewGlucoseList");
    }

    @RequestMapping(value="/homePage", method=RequestMethod.POST)
    public ModelAndView start(){
        System.out.println("Back to Home Page");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/viewGlucoseList")
    public ModelAndView viewGlucoseList(){
        glucoseList.clear();
        glucoseList = glucoseDao.getGlucoseLevels();
        return new ModelAndView("viewGlucoseList","glucoseList", glucoseList);
    }

    private Glucose getGlucoseMeasurementById(@RequestParam int id) {

        return glucoseList.stream().filter(f->f.getId()==id).findFirst().get();
    }

}