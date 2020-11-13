package com.cg.iba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.iba.entities.Role;
import com.cg.iba.entities.User;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.repository.UserRepository;
import com.cg.iba.serviceImpl.UserServiceImpl;

@SpringBootTest
class UserModuleApplicationTests {
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;
    User user = new User(2, "kum", Role.ADMIN);

    @Test
    void contextLoads() {
    }

    @Test
    public void testaddUserThrows() throws InvalidDetailsException {
        User user = new User(12, "kumari", Role.ADMIN);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userServiceImpl.addNewUser(user));
    }

    @Test
    public void testAddUser() throws InvalidDetailsException {
        when(userRepository.save(user)).thenReturn(user);
        User addedUser = null;
        addedUser = userServiceImpl.addNewUser(user);

        assertNotNull(addedUser);
    }

    @Test
    public void testAddUserEqual() throws InvalidDetailsException {
        when(userRepository.save(user)).thenReturn(user);
        User addedUser = null;
        addedUser = userServiceImpl.addNewUser(user);
        assertEquals(user, addedUser);
    }

    @Test
    public void testUpdateUser() throws InvalidDetailsException {
        userRepository.findById(user.getUserId());
    }

    @Test
    public void testDeleteUser() throws DetailsNotFoundException {
        userRepository.delete(user);
    }
    @Test
    public void testDeleteUserThrowsDetailsNotFoundException() {
        User user = new User(4,"chinni",Role.ADMIN);
        when(userRepository.findById((long) 4)).thenReturn(Optional.of(user));
        Assertions.assertThrows(DetailsNotFoundException.class, () -> {
            userServiceImpl.deleteUserInfo(5);
        });
    }
    @Test
    public void testUpdateUserThrowsInvalidDetailsException() {
        User user = new User(12, "kumari", Role.ADMIN);
        when(userRepository.findById((long) 10)).thenReturn(Optional.of(new User()));
        Assertions.assertThrows(InvalidDetailsException.class, () -> {
            userServiceImpl.updateUserInfo(user);
        });
    }
    @Test
    public void testupdateUser() throws InvalidDetailsException{
        when(userRepository.findById((long) 2)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User updateUser = userServiceImpl.updateUserInfo(user);
        assertNotNull(updateUser);
        assertEquals(user, updateUser);
    }
}
