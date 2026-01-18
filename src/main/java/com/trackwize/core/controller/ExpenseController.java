package com.trackwize.core.controller;

import com.trackwize.common.util.ResponseUtil;
import com.trackwize.core.model.dto.ExpenseReqDTO;
import com.trackwize.core.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/")
    public ResponseUtil createExpense(
            @ModelAttribute("trackingId") String trackingId,
            @ModelAttribute("userId") String userId,
            @RequestBody @Valid ExpenseReqDTO reqDTO
    ) {
        log.info("Request Payload [ExpenseReqDTO]: {}", reqDTO);
        ResponseUtil responseUtil = ResponseUtil.success();

        Long userIdL = Long.parseLong(userId);
        reqDTO.setUserId(userIdL);

        expenseService.create(reqDTO);

        responseUtil.setMsg("Expense successfully Recorded");
        return responseUtil;
    }
}
