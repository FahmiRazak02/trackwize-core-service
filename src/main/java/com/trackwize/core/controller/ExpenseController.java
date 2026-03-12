package com.trackwize.core.controller;

import com.trackwize.common.util.ResponseUtil;
import com.trackwize.core.model.dto.ExpenseListReqDTO;
import com.trackwize.core.model.dto.ExpenseReqDTO;
import com.trackwize.core.model.dto.ExpenseResDTO;
import com.trackwize.core.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/list")
    public ResponseUtil getExpenses(
            @ModelAttribute("trackingId") String trackingId,
            @ModelAttribute("userId") String userId,
            @RequestBody ExpenseListReqDTO reqDTO
            ) {
        log.info("[GET] Request Payload [userId]: {}", userId);
        ResponseUtil responseUtil = ResponseUtil.success();

        List<ExpenseResDTO> resDTOs =  expenseService.getExpenses(reqDTO);

        responseUtil.setData(resDTOs);
        return responseUtil;
    }

    @GetMapping
    public ResponseUtil getExpense(
            @ModelAttribute("trackingId") String trackingId,
            @ModelAttribute("userId") String userId,
            @RequestParam Long expenseId
    ) {
        log.info("[GET] Request Payload [expenseId]: {}", expenseId);
        ResponseUtil responseUtil = ResponseUtil.success();

        ExpenseResDTO resDTO = expenseService.get(expenseId);

        responseUtil.setData(resDTO);
        return responseUtil;
    }

    @PostMapping
    public ResponseUtil createExpense(
            @ModelAttribute("trackingId") String trackingId,
            @ModelAttribute("userId") String userId,
            @RequestBody @Valid ExpenseReqDTO reqDTO
    ) {
        log.info("[CREATE] Request Payload [ExpenseReqDTO]: {}", reqDTO);
        ResponseUtil responseUtil = ResponseUtil.success();

        Long userIdL = Long.parseLong(userId);
        reqDTO.setUserId(userIdL);

        expenseService.create(reqDTO);

        responseUtil.setMsg("Expense successfully Recorded");
        return responseUtil;
    }

    @PutMapping
    public ResponseUtil updateExpense(
            @ModelAttribute("trackingId") String trackingId,
            @ModelAttribute("userId") String userId,
            @RequestBody @Valid ExpenseReqDTO reqDTO
    ) {
        log.info("[DELETE] Request Payload [ExpenseReqDTO]: {}", reqDTO);
        ResponseUtil responseUtil = ResponseUtil.success();

        Long userIdL = Long.parseLong(userId);
        reqDTO.setUserId(userIdL);

        expenseService.update(reqDTO);

        responseUtil.setMsg("Expense successfully Updated");
        return responseUtil;
    }

    @DeleteMapping
    public ResponseUtil deleteExpense(
            @ModelAttribute("trackingId") String trackingId,
            @ModelAttribute("userId") String userId,
            @RequestParam Long expenseId
    ) {
        log.info("[DELETE] Request Payload [expenseId]: {}", expenseId);
        ResponseUtil responseUtil = ResponseUtil.success();

        expenseService.deleteById(expenseId);

        responseUtil.setMsg("Expense successfully Updated");
        return responseUtil;
    }
}
