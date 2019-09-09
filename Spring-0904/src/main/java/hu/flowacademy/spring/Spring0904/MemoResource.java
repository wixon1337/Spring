package hu.flowacademy.spring.Spring0904;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MemoResource {

    @Resource(name = "myMemoService")
    MyMemoService myMemoService;


    @PostMapping
    public void postMemo(@RequestBody Memo memo) {
        myMemoService.store(memo.getMemo());
    }

    @GetMapping
    public List<String> list() {
        return myMemoService.list().size() == 0 ? null : myMemoService.list();
    }
}
