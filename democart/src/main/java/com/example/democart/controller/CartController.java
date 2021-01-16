package com.example.democart.controller;

import com.example.democart.entity.Cart;
import com.example.democart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    CartService cartService;

    //get current cart
    @GetMapping("/cartget")
    public List<Cart> getCurrentCart()
    {
       return cartService.getCurrentCart();
    }

    @GetMapping(value="/getcart/{userId}")
    List<Cart> findByUserId(@PathVariable("userId") int userId)
    {
//        System.out.println(userId);
        return cartService.findByUserId(userId);
    }
    //add item to cart
    @PostMapping("/cartadd")
    public Cart addItemsToCart(@RequestBody Cart cart)
    {
        return this.cartService.addItemsToCart(cart);
    }
    @DeleteMapping("delproduct/{userId}/{productId}")
    public void deleteByUserIdAndProductId(@PathVariable("userId") int userId,@PathVariable (value="productId")int productId)
    {
        cartService.deleteByUserIdAndProductId(userId,productId);
    }

  @DeleteMapping("/cartdel/{userId}")

    public void deleteEntireCart(@PathVariable("userId") int userId)
  {

       cartService.deleteEntireCart(userId);
  }
  @PutMapping("/cartupd/{userId}/{productId}")
    public Cart updateQuantityOfProduct(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {

        Cart cart = cartService.findByUserIdAndProductId(userId,productId);

        return cartService.updateQuantityOfProduct(userId,productId);

  }

//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
//                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employee.setEmailId(employeeDetails.getEmailId());
//        employee.setLastName(employeeDetails.getLastName());
//        employee.setFirstName(employeeDetails.getFirstName());
//        final Employee updatedEmployee = employeeRepository.save(employee);
//        return ResponseEntity.ok(updatedEmployee);
//    }




}
