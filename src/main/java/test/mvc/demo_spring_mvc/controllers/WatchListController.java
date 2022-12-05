package test.mvc.demo_spring_mvc.controllers;
import jakarta.servlet.RequestDispatcher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import test.mvc.demo_spring_mvc.entities.WatchlistItem;

import javax.naming.Binding;

@Controller
public class WatchListController {
    List<WatchlistItem> watchlistItemLIst;
    WatchListController(){
        watchlistItemLIst=new ArrayList<>();
      /*  List<WatchlistItem> itemList=IntStream.range(1,50).mapToObj(i->{
            WatchlistItem item=new WatchlistItem();
            item.setId(i);
            item.setComment("comment "+i);
            item.setRating("ratting "+new Random().nextInt(5)+" stars");
            item.setTitle("item "+i);
            return item;
        }).collect(Collectors.toList());
*/
    }
    @GetMapping("/watchList")
    public ModelAndView getWatchList(){

        String viewName="watchlist";
        Map<String,Object> model=new HashMap<String,Object>();
        model.put("numberOfMovies",watchlistItemLIst.size());
        model.put("items",watchlistItemLIst);


        return new ModelAndView(viewName,model);
    }
    @GetMapping("watchList/form")
    public ModelAndView addItemToWatchListForm(@RequestParam(required=false) Integer itemId){
        WatchlistItem item=new WatchlistItem();
        if(itemId!=null)
            item=findWatchListItemById(itemId);

        Map<String,Object> model=new HashMap<>();
        //item.setTitle("some title");
        model.put("item",item);
        return new ModelAndView("watchItemForm",model);
    }
    @PostMapping("watchList/formSubmit")
    public ModelAndView addItemToWatchListSubmit(@Valid @ModelAttribute("item") WatchlistItem watchlistItem, BindingResult bindingResult){
        RedirectView redirectView=new RedirectView();
        if(bindingResult.hasErrors()){
            //Map<String,Object> model=new HashMap<>();
            //model.put("item",watchlistItem);
            return new ModelAndView("watchItemForm");
        }

        WatchlistItem existingWatchListItem=findWatchListItemById(watchlistItem.getId());
        if(existingWatchListItem!=null){
                watchlistItemLIst.removeIf(i->i.getId().equals(watchlistItem.getId()));
                watchlistItemLIst.add(watchlistItem);
                redirectView.setUrl("/watchList");
                return new ModelAndView(redirectView);

        }else {
            if(itemAlreadyExists(watchlistItem.getTitle())){
                bindingResult.rejectValue("title","","Title already exist in your watch list!");
                return new ModelAndView("watchItemForm");
            }

                watchlistItemLIst.add(watchlistItem);
                redirectView.setUrl("/watchList");
                return new ModelAndView(redirectView);

        }
    }
    public WatchlistItem findWatchListItemById(Integer id){
        Optional<WatchlistItem> optionalWatchlistItem=watchlistItemLIst.stream().filter(i->i.getId().equals(id)).findFirst();
        if(optionalWatchlistItem.isPresent())
            return optionalWatchlistItem.get();
        return null;
    }

    public boolean itemAlreadyExists(String itemTitle){
        for (int i = 0; i < watchlistItemLIst.size(); i++) {
            if(watchlistItemLIst.get(i).getTitle().equals(itemTitle))
                return true;
        }
        return false;
    }

}
