package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testeGivenPersonDTOThenReturnSaveMessage() {
        PersonDTO personDTO =  PersonUtils.createFakeDTO();
        Person expectedSavePerson = PersonUtils.createFakeEntity();

        when(personRepository.save(Mockito.any(Person.class))).thenReturn(expectedSavePerson);

        MessageResponseDTO expectedSuccessMessage = createdMessageSucess(expectedSavePerson.getId());

        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, succesMessage);
    }

    private MessageResponseDTO createdMessageSucess(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Create with by id " + id)
                .build();
    }
}
