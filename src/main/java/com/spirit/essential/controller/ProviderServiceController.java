package com.spirit.essential.controller;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.essential.common.web.request.pojo.ServiceInfoDetailRequest;
import com.spirit.essential.common.web.response.entity.ResultEntity;
import com.spirit.essential.service.ApplicationService;
import com.spirit.tba.exception.TbaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.spirit.essential.common.exception.ExceptionCode.UNEXPECTED_EXCEPTION;


@RestController
@RequestMapping("/provider")
@CrossOrigin
public class ProviderServiceController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/application", method = RequestMethod.GET)
    public ResultEntity getServicesInfo() {
        ResultEntity entity = new ResultEntity();
        try {
            return entity.succeed(applicationService.getApplicationInfo());
        } catch (IllegalAccessException | TbaException | InstantiationException e) {
            return entity.failed(UNEXPECTED_EXCEPTION.setTextDefault(e.getMessage()));
        } catch (MainStageException e) {
            return entity.failed(e.getResultType());
        }
    }

    @RequestMapping(value = "/application/detail", method = RequestMethod.POST)
    public ResultEntity getApplicationDetail(@RequestBody ServiceInfoDetailRequest req) {
        ResultEntity entity = new ResultEntity();
        try {
            return entity.succeed(applicationService.getApplicationDetail(req));
        } catch (IllegalAccessException | TbaException | InstantiationException e) {
            return entity.failed(UNEXPECTED_EXCEPTION.setTextDefault(e.getMessage()));
        }
        catch (MainStageException e) {
            return entity.failed(e.getResultType());
        }
    }
}
