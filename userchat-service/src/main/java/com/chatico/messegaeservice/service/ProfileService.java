package com.chatico.messegaeservice.service;

import com.chatico.messegaeservice.domain.UserChat;
import com.chatico.messegaeservice.repositiry.UserChatRepository;
import com.chatico.messegaeservice.repositiry.UserContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProfileService {
    private final UserChatRepository userChatRepository;
    private final UserContactsRepository userContactsRepository;

    @Autowired
    public ProfileService(
            UserChatRepository userChatRepository, UserContactsRepository userContactsRepository) {
        this.userChatRepository = userChatRepository;
        this.userContactsRepository = userContactsRepository;
    }

//    public UserChat changeSubscription(UserChat channel, UserChat subscriber) {
//        List<UserSubscription> subcriptions = channel.getSubscribers()
//                .stream()
//                .filter(subscription ->
//                        subscription.getSubscriber().equals(subscriber)
//                )
//                .collect(Collectors.toList());
//
//        if (subcriptions.isEmpty()) {
//            UserSubscription subscription = new UserSubscription(channel, subscriber);
//            channel.getSubscribers().add(subscription);
//        } else {
//            channel.getSubscribers().removeAll(subcriptions);
//        }
//
//        return userDetailsRepo.save(channel);
//    }
//
//    public List<UserSubscription> getSubscribers(User channel) {
//        return userSubscriptionRepo.findByChannel(channel);
//    }
//
//    public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
//        UserSubscription subscription = userSubscriptionRepo.findByChannelAndSubscriber(channel, subscriber);
//        subscription.setActive(!subscription.isActive());
//
//        return userSubscriptionRepo.save(subscription);
//    }
}
