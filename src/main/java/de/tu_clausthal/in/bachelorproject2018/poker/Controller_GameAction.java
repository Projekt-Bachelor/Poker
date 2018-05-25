package de.tu_clausthal.in.bachelorproject2018.poker;

import de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.Network_BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/GameAction")
public class Controller_GameAction {

    private static final String SUCCESS_STATUS = "SUCCESS";
    private static final String ERROR_STATUS = "ERROR";
    private static final int SUCCESS_CODE = 100;
    private static final int ERROR_CODE = 102;

    @RequestMapping(value = "/CALL")
    public Network_BaseResponse call(){
        //TODO - Include Call-Method

        return sendSuccessResponse();
    }

    @RequestMapping(value = "/RAISE")
    public Network_BaseResponse raise(@RequestParam(value = "amount", defaultValue = "0")int amount){
        //TODO - Include Raise-Method

        return sendSuccessResponse();
    }

    @RequestMapping(value = "/FOLD")
    public Network_BaseResponse fold(){
        //TODO - Include Fold-Method

        return sendSuccessResponse();
    }

    @RequestMapping(value = "/CHECK")
    public Network_BaseResponse check(){
        //TODO - Include Check-Method

        return sendSuccessResponse();
    }

    public Network_BaseResponse sendSuccessResponse(){
        Network_BaseResponse baseResponse = new Network_BaseResponse();
        baseResponse.setStatus(SUCCESS_STATUS);
        baseResponse.setCode(SUCCESS_CODE);

        return baseResponse;
    }

}
