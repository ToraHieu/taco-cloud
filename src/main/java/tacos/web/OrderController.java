package tacos.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.TacoOrder;
import tacos.TacoUser;
import tacos.data.OrderRepository;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderProps orderProps;

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo, OrderProps orderProps) {
        this.orderRepo = orderRepo;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal TacoUser user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        order.setPlaceAt(new Date());
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal TacoUser user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        List<TacoOrder> orders = orderRepo.findByUserOrderByPlaceAtDesc(user, pageable);
        model.addAttribute("orders", orders);

        return "orderList";
    }
}
