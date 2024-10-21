package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Droga;
import com.example.demo.model.Mascota;
import com.example.demo.model.Tratamiento;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class VeterinarioRepositoryTest {

	@Autowired
	private TratamientoRepository tratamientoRepository;

	@Autowired
	private VeterinarioRepository veterinarioRepository;

	@Autowired
	private MascotaRepository mascotaRepository;

	@Autowired
	private DrogaRepository drogaRepository;

	@BeforeEach
	public void setup() {
		veterinarioRepository.save(new Veterinario("Arley", "11111", "a@lol.com", "1234", "https://media.istockphoto.com/id/1389348844/es/foto/foto-de-estudio-de-una-hermosa-joven-sonriendo-mientras-está-de-pie-sobre-un-fondo-gris.jpg?s=612x612&w=0&k=20&c=kUufmNoTnDcRbyeHhU1wRiip-fNjTWP9owjHf75frFQ="));
		veterinarioRepository.save(new Veterinario("Kevin", "2222222", "be@lol.com", "4321", "https://media.istockphoto.com/id/1389348844/es/foto/foto-de-estudio-de-una-hermosa-joven-sonriendo-mientras-está-de-pie-sobre-un-fondo-gris.jpg?s=612x612&w=0&k=20&c=kUufmNoTnDcRbyeHhU1wRiip-fNjTWP9owjHf75frFQ="));
		veterinarioRepository.save(new Veterinario("Sergio", "123456789", "s@t.com", "1234", "https://i.ibb.co/0qX2b8t/sergio.jpg"));

        mascotaRepository.save(new Mascota("Ginger", "5", "4.7", "Sano", "Ninguna", "Macho", "Burmés", 
                        "https://miperroesunico.com/img/razas-de-gatos/Raza-de-Gato-Burmes.jpg"));
        mascotaRepository.save(new Mascota("Luna", "8", "5.4", "Enfermo", "Obesidad", "Hembra", "Ragdoll", 
                        "https://content.elmueble.com/medio/2023/02/24/gato-de-raza-ragdoll_5c5827ec_230224104944_900x900.jpg"));
    	mascotaRepository.save(new Mascota("Rufus", "2", "5.3", "Sano", "Ninguna", "Macho", "Siberiano", 
                        "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

		drogaRepository.save(new Droga("Cefalexina", 3.54f, 23.4f, 12, 1));

        tratamientoRepository.save(new Tratamiento("Medicamento antiinflamatorio",
                        "Reducción de inflamación y dolor", LocalDate.of(2023, 9, 1)));
        tratamientoRepository.save(new Tratamiento("Suministro de antintibiótico",
                        "Eliminación de infección bacteriana", LocalDate.of(2023, 9, 2)));
        tratamientoRepository.save(new Tratamiento("Dermatológico",
                        "Tratamiento para problemas de piel", LocalDate.of(2023, 9, 3)));
        tratamientoRepository.save(new Tratamiento("Vacunación", 
		"Aplicación de vacunas rutinarias", LocalDate.of(2023, 9, 4)));
        tratamientoRepository.save(new Tratamiento("Desparasitación",
                        "Eliminación de parásitos internos y externos", LocalDate.of(2024, 10, 5)));

		Tratamiento t = tratamientoRepository.findById(1l).get();
		Veterinario v = veterinarioRepository.findById(1l).get();
		Mascota m = mascotaRepository.findById(1l).get();
		Droga d = drogaRepository.findById(1l).get();

		t.setVeterinario(v);
		t.setMascota(m);
		t.setDroga(d);
		tratamientoRepository.save(t);
	}

	@Test
	public void VeterinarioRepository_save_Veterinario() {
		// 1. arrange
		// 2. Act
		// 3. Assert

		// arrange
		Veterinario veterinario = new Veterinario("Hola", "121212", "ne@m.ocl", "1234", "https://media.istockphoto.com/id/1389348844/es/foto/foto-de-estudio-de-una-hermosa-joven-sonriendo-mientras-está-de-pie-sobre-un-fondo-gris.jpg?s=612x612&w=0&k=20&c=kUufmNoTnDcRbyeHhU1wRiip-fNjTWP9owjHf75frFQ=\"");
		// act
		Veterinario savedVeterinario = veterinarioRepository.save(veterinario);

		// asert
		Assertions.assertThat(savedVeterinario).isNotNull();
	}

	@Test
	public void VeterinarioRepository_findAll_NotEmptyList() {
		// 1. arrange
		Veterinario v = new Veterinario("Sergio", "123456789", "s@t.com", "1234", "https://i.ibb.co/0qX2b8t/sergio.jpg");
	
		// 2. Act
		veterinarioRepository.save(v);
		List<Veterinario> veterinarios = veterinarioRepository.findAll();

		// 3. Assert
		Assertions.assertThat(veterinarios).isNotEmpty();
		Assertions.assertThat(veterinarios.size()).isEqualTo(4);
		Assertions.assertThat(veterinarios.size()).isGreaterThan(0);

	}

	@Test
	public void VeterinarioRepository_findById_FindWrongIndex() {
		// arrange
		Long index = -1l;

		// act
		Optional<Veterinario> vet = veterinarioRepository.findById(index);

		// assert
		Assertions.assertThat(vet).isEmpty();

	}

	@Test
	public void VeterinarioRepository_deleteById_EmptyVeterinario() {
		// arrange
		Long index = 2l;

		// act
		veterinarioRepository.deleteById(index);

		// assert
		Assertions.assertThat(veterinarioRepository.findById(index)).isEmpty();
	}

	@Test
	public void VeterinarioRepository_findByNombre_Veterinario() {
		// arrange
		String nombre = "Sergio";

		// act
		Optional<Veterinario> veterinario = veterinarioRepository.findByNombreIgnoreCase(nombre);

		// assert
		Assertions.assertThat(veterinario).isPresent();
	}
 
	@Test
	public void VeterinarioRepository_updateById_Veterinario() {
		// arrange
		Long index = 1l;

		// act
		Veterinario v = veterinarioRepository.findById(index).get();
		v.setNombre("Modificado Sergio");
		Veterinario updateVet = veterinarioRepository.save(v);

		// assert
		Assertions.assertThat(updateVet).isNotNull();
		Assertions.assertThat(updateVet.getNombre()).isEqualTo("Modificado Sergio");

	}

	@Test
	public void VeterinarioRepository_find(){
		// arrange
		Long index = 1l;

		// act
		List<Veterinario> veterinarios = veterinarioRepository.findByTratamientos_Id(index);

		// assert
		Assertions.assertThat(veterinarios).isNotEmpty();
		Assertions.assertThat(veterinarios.size()).isEqualTo(1);
	}
	

}