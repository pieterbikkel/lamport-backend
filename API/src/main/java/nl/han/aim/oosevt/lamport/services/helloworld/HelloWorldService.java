package nl.han.aim.oosevt.lamport.services.helloworld;

import nl.han.aim.oosevt.lamport.data.helloworld.IHelloWorldDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService implements IHelloWorldService {
    private final IHelloWorldDAO helloWorldDao;

    @Autowired
    public HelloWorldService(IHelloWorldDAO helloWorldDao) {
        this.helloWorldDao = helloWorldDao;
    }

    @Override
    public String getMessage() {
        return helloWorldDao.getHelloWorld();
    }
}