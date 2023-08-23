package com.effectivemobile.socialmediaapi.controller;

import com.effectivemobile.socialmediaapi.dto.FriendRequestDto;
import com.effectivemobile.socialmediaapi.exception.AppException;
import com.effectivemobile.socialmediaapi.mapper.FriendRequestMapper;
import com.effectivemobile.socialmediaapi.model.FriendRequest;
import com.effectivemobile.socialmediaapi.service.FriendRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/request")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Friends request", description = "The FriendRequest API. Contains operations with sending and receiving requests" +
        " for friends adding.")
@AllArgsConstructor
public class FriendRequestController {

    private final FriendRequestService friendRequestService;
    private final FriendRequestMapper friendRequestMapper;

    @Operation(summary = "Add friend.")
    @PostMapping("/{id}")
    private ResponseEntity<FriendRequest> addFriend(@PathVariable UUID id) {
        return ResponseEntity.ok(friendRequestService.addFriend(id));
    }

//    @Operation(summary = "Get out requests.")
//    @GetMapping("/out")
//    private ResponseEntity<List<FriendRequestDto>> getOutRequests() {
//        List<FriendRequestDto> outRequests = friendRequestService.getOutRequests()
//                .stream()
//                .map(friendRequestMapper::map)
//                .toList();
//        return ResponseEntity.ok(outRequests);
//    }

//    @Operation(summary = "Get in requests.")
//    @GetMapping("/in")
//    private ResponseEntity<List<FriendRequestDto>> getInRequests() {
//        List<FriendRequestDto> inRequests = friendRequestService.getInRequests()
//                .stream()
//                .map(friendRequestMapper::map)
//                .toList();
//        return ResponseEntity.ok(inRequests);
//    }

    @Operation(summary = "Accept request by its id.")
    @PostMapping("/friend-request/{id}/accept")
    private ResponseEntity<?> acceptRequest(@PathVariable UUID id) {
        List<FriendRequest> inRequests = friendRequestService.getInRequests();
        FriendRequest request = inRequests.stream()
                .filter(friendRequest -> friendRequest.getSender().getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AppException("Request with id = " + id + " not found. Source: " +
                        "FriendRequestController."));
        friendRequestService.acceptRequest(request);
        return ResponseEntity.ok("Friends request from user with id = " + id + " was accepted.");
    }

    @Operation(summary = "Decline request by its id.")
    @PostMapping("/friend-request/{id}/decline")
    private ResponseEntity<?> declineRequest(@PathVariable UUID id) {

        List<FriendRequest> inRequests = friendRequestService.getInRequests();
        FriendRequest request = inRequests.stream()
                .filter(friendRequest -> friendRequest.getSender().getId().equals(id))
                .findFirst().orElseThrow(() -> new AppException("FriendRequest with id = " + id +  "was not found. " +
                        "Source: FriendRequestController."));
        friendRequestService.declineRequest(request);
        return ResponseEntity.ok("Friends request with id = " + id + " was declined.");
    }

    @Operation(summary = "Remove friend.")
    @PostMapping("/remove/{id}")
    public ResponseEntity<?> removeFriend(@PathVariable UUID id) {
        friendRequestService.removeFriend(id);
        return ResponseEntity.ok("User with id = " + id + " was removed.");
    }
}
