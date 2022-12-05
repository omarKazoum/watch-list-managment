package test.mvc.demo_spring_mvc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class WatchListTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    public void testWatchListForm() throws Exception {
        mockMvc.perform(get("/watchList/form")).andExpect(status().is2xxSuccessful())
                .andExpect(model().size(1))
                .andExpect(view().name("watchItemForm"))
                .andExpect(model().attributeExists("item"));
    }
    @Test
    public void testWatchListFormSubmit() throws Exception {
        mockMvc.perform(post("/watchList/formSubmit")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/watchList"));
    }


}
