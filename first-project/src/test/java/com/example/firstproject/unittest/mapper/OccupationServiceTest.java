import com.example.firstproject.data.DTO.v1.OccupationDTO;
import com.example.firstproject.mapper.Mapper;
import com.example.firstproject.model.Occupation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OccupationServiceTest {

    @Test
    public void testEntityToDTOConversion() {
        // Crie uma instância Occupation para testar
        Occupation occupation = new Occupation();
        occupation.setId(1L);
        occupation.setPosition("Tester");
        occupation.setWorkload("Full-time");

        // Converta a entidade Occupation para OccupationDTO
        OccupationDTO occupationDTO = Mapper.parseObject(occupation, OccupationDTO.class);

        // Verifique se a conversão foi feita corretamente
        Assertions.assertEquals(occupation.getId(), occupationDTO.getId());
        Assertions.assertEquals(occupation.getPosition(), occupationDTO.getPosition());
        Assertions.assertEquals(occupation.getWorkload(), occupationDTO.getWorkload());
    }

    @Test
    public void testDTOToEntityConversion() {
        // Crie uma instância OccupationDTO para testar
        OccupationDTO occupationDTO = new OccupationDTO();
        occupationDTO.setId(1L);
        occupationDTO.setPosition("Tester");
        occupationDTO.setWorkload("Full-time");

        // Converta o DTO para Occupation
        Occupation occupation = Mapper.parseObject(occupationDTO, Occupation.class);

        // Verifique se a conversão foi feita corretamente
        Assertions.assertEquals(occupationDTO.getId(), occupation.getId());
        Assertions.assertEquals(occupationDTO.getPosition(), occupation.getPosition());
        Assertions.assertEquals(occupationDTO.getWorkload(), occupation.getWorkload());
    }
}
