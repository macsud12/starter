package com.macs.starter.endpoints;

import com.macs.starter.model.BasicResponse;
import com.macs.starter.model.Note;
import com.macs.starter.storage.NoteHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Maksim_Alipov.
 */
@Controller
@Component
@RequestMapping("/starter/v1/note")
public class NoteService {

    @Autowired
    private NoteHolder noteHolder;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    BasicResponse getAll() {
        List<Note> all = noteHolder.getAll(getCurrentUser());
        return new BasicResponse((all == null || all.isEmpty()) ? "empty" : all);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    BasicResponse<Boolean> post(@RequestParam(value="id", required=false, defaultValue="") String id,
            @RequestParam(value="content") String content, HttpServletResponse response) {
        if (id == null || id.isEmpty()) {
            noteHolder.save(getCurrentUser(), new Note(content));
            response.setStatus(HttpStatus.CREATED.value());
            return new BasicResponse<Boolean>(true);
        } else {
            Boolean result = noteHolder.update(getCurrentUser(), id, content);
            return new BasicResponse<Boolean>(result);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse<Boolean> delete(
            @RequestParam(value="id", required=true) String id) {
        Boolean result = noteHolder.delete(getCurrentUser(), id);
        return new BasicResponse<Boolean>(result);
    }

    private String getCurrentUser() {
        return "loggedInDummyUser";
    }
}
