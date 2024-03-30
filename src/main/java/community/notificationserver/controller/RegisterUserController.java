package community.notificationserver.controller;

import community.notificationserver.entity.DeviceDetails;
import community.notificationserver.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegisterUserController {
    private final ManagementService managementService;

    public RegisterUserController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUserDetails(@RequestBody final DeviceDetails deviceDetails) {
        return ResponseEntity.ofNullable(managementService.registerUserWithDeviceDetails(deviceDetails));
    }

    @GetMapping("")
    public ResponseEntity<DeviceDetails> fetchUserDetails(@RequestBody final String key) {
        return ResponseEntity.ofNullable((DeviceDetails) managementService.getRegisteredDeviceAndUserDetails(key));
    }
}
