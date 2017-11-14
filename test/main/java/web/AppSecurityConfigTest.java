package web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Anastasia on 12.11.2017.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppSecurityConfig.class})
@WebAppConfiguration
@SpringBootTest
public class AppSecurityConfigTest extends Assert {


    @Test
    @WithMockUser
    public void testWithDefaultWithMockUser() {
        assertEquals("user", SecurityContextHolder.getContext()
                .getAuthentication().getName());
        assertEquals("password", SecurityContextHolder.getContext()
                .getAuthentication().getCredentials().toString());
        assertEquals(1, SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().size());
        assertEquals("ROLE_USER", SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().iterator().next().getAuthority());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testWithOverridesOnWithMockUser() {
        assertEquals("admin", SecurityContextHolder.getContext()
                .getAuthentication().getName());
        assertEquals("admin", SecurityContextHolder.getContext()
                .getAuthentication().getCredentials().toString());
        assertEquals(1, SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().size());
        assertEquals("ROLE_ADMIN", SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().iterator().next().getAuthority());
    }

}
