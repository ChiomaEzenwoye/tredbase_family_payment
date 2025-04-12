package com.example.tred_base_test.service;

import com.example.tred_base_test.dto.PaymentRequestDto;
import com.example.tred_base_test.model.Parent;
import com.example.tred_base_test.model.Payment;
import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.repo.ParentRepo;
import com.example.tred_base_test.repo.PaymentRepo;
import com.example.tred_base_test.repo.StudentRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private ParentRepo parentRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Transactional
    public void processPayment(PaymentRequestDto request, String username) {
        // Find the parent using the parent ID
        Parent parent = parentRepo.findById(request.getParentId())
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        // Ensure that the parent making the payment matches the username
        if (!parent.getUserName().equals(username)) {
            throw new RuntimeException("Unauthorized: This parent is not the one making the payment");
        }

        // Find the student associated with the payment
        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        BigDecimal paymentAmount = BigDecimal.valueOf(request.getPaymentAmount());
        BigDecimal feeRate = BigDecimal.valueOf(0.05); // 5% fee
        BigDecimal adjustedAmount = paymentAmount.multiply(BigDecimal.ONE.add(feeRate));

        // If the child is shared between two parents, update both balances
        if (student.getParents().size() == 2) {
            BigDecimal share = adjustedAmount.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
            for (Parent p : student.getParents()) {
                p.setBalance(p.getBalance().subtract(share));
                parentRepo.save(p);
            }
        } else {
            // If the child has a unique parent, update only the paying parent's balance
            parent.setBalance(parent.getBalance().subtract(adjustedAmount));
            parentRepo.save(parent);
        }

        // Update the student's balance
        student.setBalance(student.getBalance().add(paymentAmount));
        studentRepo.save(student);

        // Optionally, log the payment made by the username
        log.info("Payment made by parent: {}", username);
    }


    @Override
    public Payment createPayment(Payment payment, String username) {
        payment.setCreatedBy(username);

        return paymentRepo.save(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepo.findById(id).orElse(null);
    }
}
