package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.example.demo.repositorio.AdministradorRepository;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.EnfermedadRepository;
import com.example.demo.repositorio.EspecialidadRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.RoleRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.UserRepository;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.ExcelService;

import org.springframework.boot.ApplicationArguments;

import jakarta.transaction.Transactional;

@Controller
@Transactional
@Profile("default")
public class DatabaseInit implements ApplicationRunner {

        @Autowired
        ClienteRepository clienteRepository;
        @Autowired
        EnfermedadRepository enfermedadRepository;
        @Autowired
        MascotaRepository mascotaRepository;
        @Autowired
        EspecialidadRepository especialidadRepository;
        @Autowired
        VeterinarioRepository veterinarioRepository;
        @Autowired
        DrogaRepository drogaRepository;
        @Autowired
        TratamientoRepository tratamientoRepository;
        @Autowired
        AdministradorRepository administradorRepository;
        @Autowired
        ExcelService excelService;

        @Autowired
        PasswordEncoder passwordEncoder;
        @Autowired
        UserRepository userRepo;
        @Autowired
        RoleRepository roleRepo;

        @Override
        public void run(ApplicationArguments args) throws Exception {

                roleRepo.save(new Role("CLIENTE"));
                roleRepo.save(new Role("VETERINARIO"));
                roleRepo.save(new Role("ADMINISTRADOR"));

                // Añade registros de clientes
                // 1. Crear el objeto
                // 2. Guardarlo en la tabla user
                // 3. Agregar el objeto del paso 1, el ID obtenido en el paso 2
                // 4. Guardar el objeto en la tabla cliente

                List<Cliente> clientsUsers = new ArrayList<>();

                clientsUsers.add(new Cliente("Juan Carlos", "1435466", "juancarlos@gmail.com", "2343544354",
                                "Cll 79 # 13-21",
                                "https://img.a.transfermarkt.technology/portrait/big/94540-1636851420.jpg?lm=1"));
                clientsUsers.add(new Cliente("Pedro", "14789806", "pedro@gmail.com", "4366854", "Cll 80 # 89-21",
                                "https://ntvb.tmsimg.com/assets/assets/494807_v9_bd.jpg?w=360&h=480"));
                clientsUsers.add(new Cliente("Carlos Luis", "2234466", "carlos@gmail.com", "546544354",
                                "Cr 9 # 130-21",
                                "https://ntvb.tmsimg.com/assets/assets/494807_v9_bd.jpg?w=360&h=480"));
                clientsUsers.add(new Cliente("Luis Alberto", "14789808", "luis@gmail.com", "56766854",
                                "Cll 79 # 13-21",
                                "https://img.peliplat.com/api/resize/v1?imagePath=std/202501/a/2/a216e91526720344073201406fb3bee0.jpg&mode=FILL&width=304&height=456&limit=false"));
                clientsUsers.add(new Cliente("Ana María", "1234567", "anamaria@gmail.com", "3001234567",
                                "Cll 9 #3-2",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Ana_de_Armas_GQ_2018_2.png/330px-Ana_de_Armas_GQ_2018_2.png"));
                clientsUsers.add(new Cliente("Miguel Ángel", "2345678", "miguelangel@gmail.com", "3002345678",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/Snoop_Dogg_2019_by_Glenn_Francis.jpg/330px-Snoop_Dogg_2019_by_Glenn_Francis.jpg"));
                clientsUsers.add(new Cliente("Laura Gómez", "3456789", "lauragomez@gmail.com", "3003456789",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Laura_Dern_Deauville_2017.jpg/330px-Laura_Dern_Deauville_2017.jpg"));
                clientsUsers.add(new Cliente("Sofía Martínez", "4567890", "sofia@gmail.com", "3004567890",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Sofía_Vergara_in_2020.jpg/330px-Sofía_Vergara_in_2020.jpg"));
                clientsUsers.add(new Cliente("Javier Pérez", "5678901", "javierperez@gmail.com", "3005678901",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/David_Beckham_UNICEF_%28cropped%29.jpg/263px-David_Beckham_UNICEF_%28cropped%29.jpg"));
                clientsUsers.add(new Cliente("Valentina López", "6789012", "valentina@gmail.com", "3006789012",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Serena_Williams_at_2013_US_Open.jpg/368px-Serena_Williams_at_2013_US_Open.jpg"));
                clientsUsers.add(new Cliente("Daniel Rodríguez", "7890123", "danielr@gmail.com", "3007890123",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/James_al_natural_-_Series%2C_01.jpg/338px-James_al_natural_-_Series%2C_01.jpg"));
                clientsUsers.add(new Cliente("Isabella Ramírez", "8901234", "isabella@gmail.com", "3008901234",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/4/41/Let_It_Snow_cast_vlogbrothers_1_%28cropped%29.png"));
                clientsUsers.add(new Cliente("Andrés Morales", "1234568", "andres@gmail.com", "3001234568",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/ChrisEvans2025.jpg/330px-ChrisEvans2025.jpg"));
                clientsUsers.add(new Cliente("Sara González", "2345679", "sara@gmail.com", "3002345679",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Sarah_Paulson_77th_Tony_Awards_2024.jpg/330px-Sarah_Paulson_77th_Tony_Awards_2024.jpg"));
                clientsUsers.add(new Cliente("Luis Miguel", "3456780", "luismiguel@gmail.com", "3003456780",
                                "Cll 79 # 13-21",
                                "https://indiehoy.com/wp-content/uploads/2025/09/paul-mccartney-foto.jpg"));
                clientsUsers.add(new Cliente("Natalia Vargas", "4567891", "natalia@gmail.com", "3004567891",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Natalie_Portman_2025.jpg/330px-Natalie_Portman_2025.jpg"));
                clientsUsers.add(new Cliente("Camilo Hernández", "5678902", "camilo@gmail.com", "3005678902",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Denzel_Washington_2018.jpg/375px-Denzel_Washington_2018.jpg"));
                clientsUsers.add(new Cliente("Marta Castaño", "6789013", "marta@gmail.com", "3006789013",
                                "Cll 79 # 13-21",
                                "https://www.rockandpop.cl/wp-content/uploads/2022/01/357a028ac38c548a047fc82906d27d5c.jpg"));
                clientsUsers.add(new Cliente("Oscar Jiménez", "7890124", "oscar@gmail.com", "3007890124",
                                "Cll 79 # 13-21",
                                "https://pics.filmaffinity.com/samuel_l_jackson-281405475762864-nm_200.jpg"));
                clientsUsers.add(new Cliente("Juliana Díaz", "8901235", "juliana@gmail.com", "3008901235",
                                "Cll 79 # 13-21",
                                "https://pics.filmaffinity.com/julianne_moore-129461114251768-nm_200.jpg"));
                clientsUsers.add(new Cliente("Alejandro Ruiz", "9012346", "alejandro@gmail.com", "3009012346",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Robert_De_Niro_Cannes_2016.jpg/330px-Robert_De_Niro_Cannes_2016.jpg"));
                clientsUsers.add(new Cliente("Claudia Moreno", "0123457", "claudia@gmail.com", "3000123457",
                                "Cll 79 # 13-21",
                                "https://i.pinimg.com/1200x/3e/37/b6/3e37b650cfa907d6267a70203b68fdfe.jpg"));
                clientsUsers.add(new Cliente("Héctor Castro", "1234569", "hector@gmail.com", "3001234569",
                                "Cll 79 # 13-21",
                                "https://www.angelfire.com/ny/conexion/lavoe_hector.jpeg"));
                clientsUsers.add(new Cliente("Lina Torres", "2345680", "lina@gmail.com", "3002345680",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Emma_Watson_2013.jpg/300px-Emma_Watson_2013.jpg"));
                clientsUsers.add(new Cliente("Santiago Pineda", "3456791", "santiago@gmail.com", "3003456791",
                                "Cll 79 # 13-21",
                                "https://www.quesomecanico.com/web/media/bearleague/thumb/bl16406488131020.png"));
                clientsUsers.add(new Cliente("Melissa Morales", "4567802", "melissa@gmail.com", "3004567802",
                                "Cll 79 # 13-21",
                                "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/1F0fCPNhb5W0WyFe8Tszfbx1DEp.jpg"));
                clientsUsers.add(new Cliente("Felipe Cortés", "5678913", "felipec@gmail.com", "3005678913",
                                "Cll 79 # 13-21",
                                "https://i.pinimg.com/564x/10/2a/fe/102afe8b7ac03cb144244226c77f56a8.jpg"));
                clientsUsers.add(new Cliente("Andrea Ruiz", "6789024", "andrea@gmail.com", "3006789024",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Arianna_Huffington_2011_Shankbone_2.JPG/597px-Arianna_Huffington_2011_Shankbone_2.JPG"));
                clientsUsers.add(new Cliente("María Fernanda", "8901246", "mariaf@gmail.com", "3008901246",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/María_Becerra_2025_03.jpg/330px-María_Becerra_2025_03.jpg"));
                clientsUsers.add(new Cliente("David Quintero", "9012357", "david@gmail.com", "3009012357",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/María_Becerra_2025_03.jpg/330px-María_Becerra_2025_03.jpg"));
                clientsUsers.add(new Cliente("Laura Carolina", "0123468", "lauracarolina@gmail.com", "3000123468",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Meryl_Streep_December_2018.jpg/330px-Meryl_Streep_December_2018.jpg"));
                clientsUsers.add(new Cliente("Carlos Martínez", "1234570", "carlosm@gmail.com", "3001234570",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/3/34/Charlie_Chaplin_portrait.jpg"));
                clientsUsers.add(new Cliente("Paola Hernández", "2345681", "paola@gmail.com", "3002345681",
                                "Cll 79 # 13-21",
                                "https://www.hola.com/imagenes/belleza/actualidad/20190306138482/penelope-cruz-joven-trucos-maquillaje-pelo/0-655-413/penelope-cruz-belleza-chanel-a.jpg"));
                clientsUsers.add(new Cliente("Jorge Salgado", "3456792", "jorge@gmail.com", "3003456792",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Michael_Jordan_in_2014.jpg/330px-Michael_Jordan_in_2014.jpg"));
                clientsUsers.add(new Cliente("Laura Vásquez", "4567803", "laurav@gmail.com", "3004567803",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Laura_Bozzo_2014.png/330px-Laura_Bozzo_2014.png"));
                clientsUsers.add(new Cliente("Mateo Ramírez", "5678914", "mateo@gmail.com", "3005678914",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jp"));
                clientsUsers.add(new Cliente("Ricardo Álvarez", "7890136", "ricardo@gmail.com", "3007890136",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Eminem_2021_Color_Corrected.jpg/800px-Eminem_2021_Color_Corrected.jpg"));
                clientsUsers.add(new Cliente("Tatiana Moreno", "8901247", "tatiana@gmail.com", "3008901247",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/9/97/Tania_Raymonde_2017_%28xwaKtuOkhX8%29.jpg"));
                clientsUsers.add(new Cliente("Santiago Pérez", "123456", "santiago@gmail.com", "3009012358",
                                "Cll 79 # 13-21",
                                "https://s3.abcstatics.com/Media/201404/05/cobain-foto--644x600.jpg"));
                clientsUsers.add(new Cliente("Daniela Ríos", "0123469", "daniela@gmail.com", "3000123469",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Angelina-Jolie_cropped.jpg/800px-Angelina-Jolie_cropped.jpg"));
                clientsUsers.add(new Cliente("Cristian Ortiz", "1234571", "cristian@gmail.com", "3001234571",
                                "Cll 79 # 13-21",
                                "https://images.ladepeche.fr/api/v1/images/view/5c1caedb3e454646793fbf72/full/image.jpg"));
                clientsUsers.add(new Cliente("Mónica Silva", "2345682", "monica@gmail.com", "3002345682",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Monica_Bellucci_2006.jpg/330px-Monica_Bellucci_2006.jpg"));
                clientsUsers.add(new Cliente("Gustavo Díaz", "3456793", "gustavo@gmail.com", "3003456793",
                                "Cll 79 # 13-21",
                                "https://www.fundacionkonex.org/custom/web/data/imagenes/repositorio/2010/6/1/1315/201603161112507f53f8c6c730af6aeb52e66eb74d8507.jpg"));
                clientsUsers.add(new Cliente("Laura Jiménez", "4567804", "lauraj@gmail.com", "3004567804",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Mary_Elizabeth_Winstead_by_Gage_Skidmore.jpg/330px-Mary_Elizabeth_Winstead_by_Gage_Skidmore.jpg"));
                clientsUsers.add(new Cliente("David Fernández", "5678915", "davidf@gmail.com", "3005678915",
                                "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/David_Ospina%2C_2015-05-31_%28cropped%29.JPG/330px-David_Ospina%2C_2015-05-31_%28cropped%29.JPG"));
                clientsUsers.add(new Cliente("Valeria Romero", "6789026", "valeria@gmail.com", "3006789026",
                                "Cll 79 # 13-21",
                                "https://es.web.img2.acsta.net/c_310_420/pictures/20/06/30/18/30/5671237.jpg"));
                clientsUsers.add(new Cliente("Emanuel Calderón", "7890137", "emanuel@gmail.com", "3007890137",
                                "Cll 79 # 13-21",
                                "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/kOJelnLSb89SeivbOCt1l94Hz2d.jpg"));
                clientsUsers.add(new Cliente("Natalia Martínez", "8901248", "natalia.martinez@gmail.com",
                                "3008901248", "Cll 79 # 13-21",
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Gal_Gadot_2018_cropped_retouched.jpg/330px-Gal_Gadot_2018_cropped_retouched.jpg"));
                clientsUsers.add(new Cliente("Manuel Martínez", "9012359", "manuel.martinez@gmail.com",
                                "3009012359", "Cll 79 # 13-21",
                                "https://es.web.img3.acsta.net/c_310_420/pictures/15/11/20/15/12/197269.jpg"));
                clientsUsers.add(new Cliente("Elena Torres", "9876543", "elena.torres@gmail.com", "3009876543",
                                "Cll 50 # 12-34",
                                "https://ntvb.tmsimg.com/assets/assets/494807_v9_bd.jpg?w=360&h=480"));

                saveClientesWithUserEntities(clientsUsers);

                // agregar mascotas
                mascotaRepository.save(new Mascota("Toby", "5", "4", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Manchas", "3", "6", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Orion", "4", "5", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Linsy", "5", "4.5", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Trixy", "3", "6", "Hembra", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                // ENFERMEDADES
                enfermedadRepository.save(Enfermedad.builder().nombre("Sin enfermedad").sintomas("La mascota está saludable").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Gripe Felina").sintomas("Resfriado común con estornudos, ojos llorosos y fiebre").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Infección Respiratoria Superior").sintomas("Congestión nasal, tos y pérdida de apetito").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Dermatitis Felina").sintomas("Irritación en la piel, enrojecimiento y picazón").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Otitis").sintomas("Infección del oído, mal olor y rascado excesivo").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Cistitis Felina").sintomas("Dolor al orinar, sangre en la orina y letargo").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Gastroenteritis").sintomas("Vómitos, diarrea y deshidratación").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Conjuntivitis").sintomas("Ojos irritados, secreción ocular y parpadeo constante").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Parásitos Intestinales").sintomas("Pérdida de peso, vómitos y diarrea").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Moquillo Felino").sintomas("Secreción nasal, fiebre y pérdida de apetito").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Infección Urinaria").sintomas("Dificultad al orinar, orina turbia y olor fuerte").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Panleucopenia").sintomas("Fiebre alta, vómitos y deshidratación").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Anemia Infecciosa Felina").sintomas("Letargo, pérdida de apetito y encías pálidas").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Toxoplasmosis").sintomas("Diarrea, vómitos y fiebre").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Leucemia Felina").sintomas("Pérdida de peso, infecciones recurrentes y letargo").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Tiña Felina").sintomas("Lesiones circulares en la piel y pérdida de pelaje").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Artritis Felina").sintomas("Rigidez en las articulaciones y dificultad para moverse").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Rabia Felina").sintomas("Agitación, cambios de comportamiento y agresividad").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Calicivirus").sintomas("Úlceras en la boca, fiebre y secreción nasal").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Peritonitis Infecciosa Felina").sintomas("Fiebre, inflamación abdominal y pérdida de apetito").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Asma Felina").sintomas("Tos, dificultad para respirar y silbidos al exhalar").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Enfermedad periodontal").sintomas("Inflamación de los tejidos que rodean los dientes, puede causar mal aliento y pérdida dental").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Gingivitis felina").sintomas("Inflamación de las encías, puede provocar dolor y dificultad para comer").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Miocardiopatía hipertrófica").sintomas("Engrosamiento del músculo cardíaco, puede causar insuficiencia cardíaca").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Enfermedad valvular").sintomas("Afección de las válvulas del corazón que puede provocar soplos cardíacos").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Linfoma felino").sintomas("Cáncer que afecta los ganglios linfáticos y otros órganos").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Tumores mamarios").sintomas("Crecimientos anormales en las glándulas mamarias, pueden ser benignos o malignos").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Piometra").sintomas("Infección del útero que puede ser potencialmente mortal").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Aborto espontáneo").sintomas("Pérdida del embarazo en etapas avanzadas, puede ser causada por infecciones o problemas hormonales").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Reacciones adversas a la anestesia").sintomas("Complicaciones que pueden surgir durante o después de la anestesia").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Hipotensión anestésica").sintomas("Descenso en la presión arterial durante la anestesia que puede ser peligroso").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Obesidad felina").sintomas("Acumulación excesiva de grasa que puede provocar enfermedades secundarias").build());
                enfermedadRepository.save(Enfermedad.builder().nombre("Enfermedades gastrointestinales").sintomas("Trastornos que afectan el tracto digestivo, pueden causar vómitos y diarrea").build());

                // Mascotas
                mascotaRepository.save(new Mascota("Nina", "4", "5", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Rex", "3", "5.5", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Luna", "4", "4", "Hembra", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Tom", "6", "5", "Macho", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Bella", "3", "5.5", "Hembra", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Oreo", "4", "5", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Cleo", "3", "4.5", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Leo", "5", "6", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Sasha", "7", "5", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Milo", "4", "5.5", "Macho", "Siberiano",
                                "https://images.unsplash.com/photo-1536590158209-e9d615d525e4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8R2F0b3N8ZW58MHx8MHx8fDA%3D"));

                mascotaRepository.save(new Mascota("Simba", "5", "4.2", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Misty", "3", "3.8", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Whiskers", "6", "5.0", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Lola", "8", "4.5", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Charlie", "4", "6.2", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Pepper", "5", "4.1", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Nala", "3", "3.9", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Zeus", "7", "5.5", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "9", "4.8", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Buddy", "4", "5.5", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Jack", "4", "5.0", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Minnie", "3", "3.5", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Smokey", "5", "4.8", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Sandy", "8", "6.2", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Rusty", "2", "5.5", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Milo", "4", "4.0", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Oreo", "3", "4.1", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Cleo", "6", "5.0", "Hembra", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Tommy", "9", "6.0", "Macho", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Zara", "5", "5.3", "Hembra", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Dexter", "4", "5.2", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "3", "4.0", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Oscar", "5", "4.8", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Penny", "8", "6.0", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Fritz", "2", "5.5", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Juno", "4", "4.4", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Milo", "3", "4.2", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Bella", "6", "5.3", "Hembra", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Rex", "9", "6.1", "Macho", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Luna", "5", "5.5", "Hembra", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Nina", "4", "4.5", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Cleo", "3", "3.9", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Leo", "5", "5.1", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Sasha", "8", "6.3", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Max", "2", "5.4", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Toby", "4", "4.0", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Rex", "3", "5.1", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Oreo", "5", "4.5", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Trixy", "8", "6.0", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Manchas", "2", "5.8", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Pip", "4", "4.2", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Chloe", "3", "3.8", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Mercurio", "5", "4.9", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Lola", "9", "6.5", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Sam", "6", "5.2", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Jasper", "4", "4.0", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Fifi", "3", "3.5", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Tiger", "5", "4.8", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Ella", "8", "5.0", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Marty", "2", "5.5", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Jack", "4", "4.1", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Penny", "3", "3.9", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Ginger", "5", "4.7", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Luna", "8", "5.4", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Rufus", "2", "5.3", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Lily", "4", "4.2", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Oscar", "3", "3.8", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Sammy", "5", "4.6", "Hembra", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "8", "5.7", "Macho", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Cleo", "2", "5.0", "Hembra", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Dexter", "4", "4.0", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Luna", "3", "3.8", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Charlie", "5", "4.5", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Maggie", "8", "6.0", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Rusty", "2", "5.1", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Oscar", "4", "4.2", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Zara", "3", "3.9", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Buddy", "5", "4.8", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Ellie", "8", "5.5", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Max", "6", "5.0", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Ziggy", "4", "4.0", "Macho", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Nina", "3", "3.5", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Finn", "5", "4.8", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Lola", "9", "6.3", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Leo", "3", "5.2", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                mascotaRepository.save(new Mascota("Misty", "4", "4.1", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Gizmo", "3", "4.5", "Macho", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Apollo", "5", "4.9", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Nala", "9", "6.0", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Max", "2", "5.5", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));
                mascotaRepository.save(new Mascota("Luna", "4", "4.3", "Hembra", "Birmano",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Daisy", "3", "3.6", "Hembra", "Persa",
                                "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian-Long-Hair.jpg?itok=AOnt5aNF"));
                mascotaRepository.save(new Mascota("Buster", "5", "4.7", "Macho", "Burmés",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Mia", "9", "6.4", "Hembra", "Ragdoll",
                                "https://static.vecteezy.com/system/resources/previews/025/436/159/non_2x/cat-sit-black-silhouette-domestic-pet-profile-cat-for-print-card-sticker-illustration-vector.jpg"));
                mascotaRepository.save(new Mascota("Rusty", "2", "5.0", "Macho", "Siberiano",
                                "https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_126848656-1024x995.jpg"));

                // ESPECIALIDADES
                especialidadRepository.save(Especialidad.builder()
                .nombre("Ortopédico").caracteristicas("Tratamiento de las articulaciones").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Dermatología").caracteristicas("Diagnóstico y tratamiento de enfermedades de la piel").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Odontología Veterinaria").caracteristicas("Cuidado y tratamiento dental").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Cardiología").caracteristicas("Diagnóstico y tratamiento de enfermedades del corazón").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Oncología").caracteristicas("Diagnóstico y tratamiento de cáncer en animales").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Medicina Interna").caracteristicas("Tratamiento de enfermedades internas").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Reproducción y Obstetricia").caracteristicas("Manejo de la reproducción y cuidado durante el parto").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Anestesiología").caracteristicas("Administración de anestesia y manejo del dolor").build());
        
                especialidadRepository.save(Especialidad.builder()
                .nombre("Nutrición Veterinaria").caracteristicas("Asesoría en dietas y alimentación para mascotas").build());

                // VETERINARIOS
                List<Veterinario> vetsUsers = new ArrayList<>();

                vetsUsers.add(new Veterinario("Sergio", "123456789", "s@t.com", "1234",
                                "https://ibb.co/nq2t6DW", true));
                vetsUsers.add(new Veterinario("Carlos", "987654321", "c@vets.com", "2345",
                                "https://ibb.co/x76Xm4C", true));
                vetsUsers.add(new Veterinario("Laura", "456789123", "l@vetmail.com", "3456",
                                "https://ibb.co/BBGLpQ3", true));
                vetsUsers.add(new Veterinario("Andrés", "321456987", "a@clinic.com", "4567",
                                "https://ibb.co/YTZ53rd", true));
                vetsUsers.add(new Veterinario("Fernanda", "654321789", "f@catvets.com", "5678",
                                "https://ibb.co/n7J20b3", true));
                vetsUsers.add(new Veterinario("Ricardo", "123987654", "r@medvet.com", "6789",
                                "https://ibb.co/pnNWsG8", true));
                vetsUsers.add(new Veterinario("Paula", "789456123", "p@vetservice.com", "7890",
                                "https://ibb.co/6whRfQ7", true));
                vetsUsers.add(new Veterinario("María", "321654987", "m@vetclinic.com", "8901",
                                "https://ibb.co/fQ9cVPX", true));
                vetsUsers.add(new Veterinario("Javier", "456123789", "j@animalhealth.com", "9012",
                                "https://ibb.co/f2WnPxn", true));
                vetsUsers.add(new Veterinario("Ana", "789123456", "ana@vetcare.com", "0123",
                                "https://ibb.co/kc7f2bV", true));
                vetsUsers.add(new Veterinario("Pedro", "987321654", "pedro@cathealth.com", "1235",
                                "https://ibb.co/TWmmLmc", true));
                vetsUsers.add(new Veterinario("Daniela", "654789123", "daniela@felineclinic.com", "2346",
                                "https://ibb.co/LkTxXk8", true));
                vetsUsers.add(new Veterinario("Gustavo", "123789654", "gustavo@animalclinic.com", "3457",
                                "https://ibb.co/Jmp08Ny", true));
                vetsUsers.add(new Veterinario("Lucía", "789321456", "lucia@vetprofession.com", "4568",
                                "https://ibb.co/Sd7jzD8", true));
                vetsUsers.add(new Veterinario("Mauricio", "321987654", "mauricio@petcare.com", "5679",
                                "https://ibb.co/vkNB1xj", true));
                vetsUsers.add(new Veterinario("Adriana", "654123987", "adriana@vetservice.com", "6780",
                                "https://ibb.co/FYH7P6g", true));
                vetsUsers.add(new Veterinario("Diego", "987456123", "diego@animaldoctor.com", "7891",
                                "https://ibb.co/cFML9x7", true));
                vetsUsers.add(new Veterinario("Claudia", "456789321", "claudia@clinicvet.com", "8902",
                                "https://ibb.co/6Wtnv6H", false));

                saveVeterinariosWithUserEntities(vetsUsers);

                // Tratamientos
                tratamientoRepository.save(new Tratamiento("Medicamento antiinflamatorio",
                                "Reducción de inflamación y dolor", LocalDate.of(2025, 1, 1)));
                tratamientoRepository.save(new Tratamiento("Suministro de antintibiótico",
                                "Eliminación de infección bacteriana", LocalDate.of(2025, 1, 2)));
                tratamientoRepository.save(new Tratamiento("Dermatológico",
                                "Tratamiento para problemas de piel", LocalDate.of(2025, 1, 3)));
                tratamientoRepository.save(new Tratamiento("Vacunación", "Aplicación de vacunas rutinarias",
                                LocalDate.of(2025, 1, 4)));
                tratamientoRepository.save(new Tratamiento("Desparasitación",
                                "Eliminación de parásitos internos y externos", LocalDate.of(2025, 1, 5)));
                tratamientoRepository.save(new Tratamiento("Tratamiento para la ansiedad",
                                "Manejo de ansiedad en mascotas", LocalDate.of(2025, 1, 6)));
                tratamientoRepository.save(new Tratamiento("Hormonal",
                                "Regulación de desequilibrios hormonales", LocalDate.of(2025, 1, 7)));
                tratamientoRepository.save(new Tratamiento("Cirugía menor", "Procedimientos quirúrgicos menores",
                                LocalDate.of(2025, 1, 8)));
                tratamientoRepository.save(new Tratamiento("Regulación de la obesidad", "Plan de reducción de peso",
                                LocalDate.of(2025, 1, 9)));
                tratamientoRepository.save(new Tratamiento("Rehabilitación física",
                                "Terapias para recuperación de movilidad", LocalDate.of(2025, 1, 10)));
                tratamientoRepository.save(new Tratamiento("Tratamiento para la diabetes",
                                "Plan de control de la diabetes", LocalDate.of(2025, 1, 11)));
                tratamientoRepository.save(new Tratamiento("Regulamiento de la hipertensión",
                                "Plan de control de la hipertensión", LocalDate.of(2025, 1, 12)));

                // Administradores
                List<Administrador> adminUsers = new ArrayList<>();
                adminUsers.add(new Administrador("Arley", "1111"));
                adminUsers.add(new Administrador("Kevin", "2222"));
                adminUsers.add(new Administrador("Daniel", "3333"));

                saveAdministradoresWithUserEntities(adminUsers);

                // Drogas
                excelService.cargarDrogasDesdeExcel();

                // Asignar mascotas aleatoriamente a clientes
                List<Mascota> mascotas = mascotaRepository.findAll();
                List<Enfermedad> enfermedades = enfermedadRepository.findAll();
                List<Cliente> clientes = clienteRepository.findAll();
                List<Veterinario> veterinarios = veterinarioRepository.findAll();
                List<Especialidad> especialidades = especialidadRepository.findAll();
                List<Tratamiento> tratamientos = tratamientoRepository.findAll();
                List<Droga> drogas = drogaRepository.findAll();

                // Asociar estados a las mascotas
                asignarEstadosAMascotas(mascotas);

                // Asociar enfermedades a las mascotas
                asignarEnfermedadesAMascotas(enfermedades, mascotas);

                // Asocia aleatoriamente dos mascotas a cada cliente
                asignarMascotasAClientes(clientes, mascotas);

                // Asocia aleatoriamente dos especialidades a cada veterinario.
                asignarEspecialidadesAVeterinarios(veterinarios, especialidades);

                // Asigna valores a un tratamiento
                asignarRelacionesATratamientos(veterinarios, tratamientos, drogas, mascotas);

        }

        /* Cosas de Seguridad */
        public void saveClientesWithUserEntities(List<Cliente> clientes) {
                for (Cliente cliente : clientes) {
                        // Crea un userEntity a partir del cliente
                        UserEntity userEntity = saveUserCliente(cliente);
                        // Asigna el userEntity al cliente
                        cliente.setUser(userEntity);
                        // Guarda el cliente en el repositorio
                        clienteRepository.save(cliente);
                }
        }

        public void saveVeterinariosWithUserEntities(List<Veterinario> veterinarios) {
                for (Veterinario veterinario : veterinarios) {
                        // Crea un userEntity a partir del veterinario
                        UserEntity userEntity = saveUserVeterinario(veterinario);
                        // Asigna el userEntity al veterinario
                        veterinario.setUser(userEntity);
                        // Guarda el veterinario en el repositorio
                        veterinarioRepository.save(veterinario);
                }
        }

        public void saveAdministradoresWithUserEntities(List<Administrador> adminUsers) {
                for (Administrador adminUser : adminUsers) {
                        // Crea un userEntity a partir del administrador
                        UserEntity userEntity = saveUserAdministrador(adminUser);
                        // Asigna el userEntity al administrador
                        adminUser.setUser(userEntity);
                        // Guarda el administrador en el repositorio
                        administradorRepository.save(adminUser);
                }
        }

        // Cierra seguridad


        private void asignarEstadosAMascotas(List<Mascota> mascotas) {
                Random random = new Random(); // Se crea una instancia de Random para generar valores aleatorios.
                for (Mascota mascota : mascotas) { // Itera a través de la lista de mascotas.
                        // Asigna un estado de salud aleatorio a cada mascota:
                        mascota.setEstado(random.nextBoolean() ? EstadoSalud.SANO : EstadoSalud.ENFERMO);
                }
        }

        private void asignarEnfermedadesAMascotas(List<Enfermedad> enfermedades, List<Mascota> mascotas) {
                Random random = new Random();

                for (Mascota mascota : mascotas) {
                        if (mascota.getEnfermedad() == null && mascota.getEstado() == EstadoSalud.ENFERMO) {
                                mascota.setEnfermedad(enfermedades.get(random.nextInt(enfermedades.size() - 1) + 1));
                        }
                }
        }

        private void asignarMascotasAClientes(List<Cliente> clientes, List<Mascota> mascotas) {
                Random random = new Random();
                List<Mascota> mascotasDisponibles = new ArrayList<>(mascotas);

                for (Cliente cliente : clientes) {
                        if (!(mascotasDisponibles.size() < 2) && cliente != null) {
                                // Selecciona dos mascotas aleatorias
                                List<Mascota> mascotasSeleccionadas = random.ints(0, mascotasDisponibles.size())
                                                .distinct()
                                                .limit(2)
                                                .mapToObj(mascotasDisponibles::get)
                                                .collect(Collectors.toList());

                                // Asocia las mascotas seleccionadas con el cliente
                                for (Mascota mascota : mascotasSeleccionadas) {
                                        mascota.setCliente(cliente);
                                        mascotasDisponibles.remove(mascota); // Elimina la mascota de la lista de
                                                                             // disponibles
                                }
                        }
                }
        }

        private void asignarEspecialidadesAVeterinarios(List<Veterinario> veterinarios,
                        List<Especialidad> especialidades) {
                Random random = new Random();

                for (Veterinario veterinario : veterinarios) {
                        if (veterinario != null) {
                                veterinario.setEspecialidad(especialidades.get(random.nextInt(especialidades.size())));
                        }
                }
        }

        private void asignarRelacionesATratamientos(List<Veterinario> veterinarios, List<Tratamiento> tratamientos,
                        List<Droga> drogas, List<Mascota> mascotas) {
                Random random = new Random();

                // Asignar los mismos datos para los primeros tres tratamientos
                if (!veterinarios.isEmpty() && !drogas.isEmpty() && !mascotas.isEmpty()) {
                        Veterinario veterinarioFijo = veterinarios.get(0); // El primer veterinario disponible
                        Droga drogaFija = drogas.get(0); // La primera droga disponible
                        Mascota mascotaFija = mascotas.get(0); // La primera mascota disponible
                        mascotaFija.setEstado(EstadoSalud.ENFERMO);

                        for (int i = 0; i < 3 && i < tratamientos.size(); i++) {
                                Tratamiento tratamiento = tratamientos.get(i);
                                veterinarioFijo.agregarTratamiento(tratamiento);
                                drogaFija.agregarTratamiento(tratamiento);
                                mascotaFija.agregarTratamiento(tratamiento, EstadoSalud.OBSERVACION); // Estado final

                                mascotaFija.setEstado(EstadoSalud.ENFERMO);
                        }
                }

                // Asignar datos aleatorios para el resto de los tratamientos
                for (int i = 3; i < tratamientos.size(); i++) {
                        Tratamiento tratamiento = tratamientos.get(i);

                        // Encontrar un veterinario disponible
                        Veterinario veterinarioAsignado = null;
                        for (int j = 0; j < veterinarios.size(); j++) {
                                Veterinario veterinario = veterinarios.get(random.nextInt(veterinarios.size()));
                                if (veterinario.getEstado()) {
                                        veterinarioAsignado = veterinario;
                                        break;
                                }
                        }

                        // Encontrar una droga con unidades disponibles
                        Droga drogaAsignada = null;
                        for (int j = 0; j < drogas.size(); j++) {
                                Droga droga = drogas.get(random.nextInt(drogas.size()));
                                if (droga.getUnidadesDisponibles() > 0) {
                                        drogaAsignada = droga;
                                        break;
                                }
                        }

                        // Encontrar una mascota que esté enferma
                        Mascota mascotaAsignada = null;
                        for (int j = 0; j < mascotas.size(); j++) {
                                Mascota mascota = mascotas.get(random.nextInt(mascotas.size()));
                                if (mascota.getEstado() == EstadoSalud.ENFERMO) {
                                        mascotaAsignada = mascota;
                                        break;
                                }
                        }

                        // Si todos los elementos necesarios están disponibles, asignarlos al
                        // tratamiento
                        if (veterinarioAsignado != null && drogaAsignada != null && mascotaAsignada != null) {
                                veterinarioAsignado.agregarTratamiento(tratamiento);
                                drogaAsignada.agregarTratamiento(tratamiento);
                                mascotaAsignada.agregarTratamiento(tratamiento, EstadoSalud.OBSERVACION);
                        }
                }
        }

        private UserEntity saveUserCliente(Cliente cliente) {
                UserEntity userEntity = new UserEntity();

                userEntity.setUsername(cliente.getCedula());
                userEntity.setPassword(passwordEncoder.encode("1234"));

                Role roles = roleRepo.findByName("CLIENTE").get();
                userEntity.setRoles(List.of(roles));

                return userRepo.save(userEntity);
        }

        private UserEntity saveUserAdministrador(Administrador administrador) {
                UserEntity userEntity = new UserEntity();

                userEntity.setUsername(administrador.getUsuario());
                userEntity.setPassword(passwordEncoder.encode(administrador.getPassword()));

                Role roles = roleRepo.findByName("ADMINISTRADOR").get();
                userEntity.setRoles(List.of(roles));

                return userRepo.save(userEntity);
        }

        private UserEntity saveUserVeterinario(Veterinario veterinario) {
                UserEntity userEntity = new UserEntity();

                userEntity.setUsername(veterinario.getCorreo());
                userEntity.setPassword(passwordEncoder.encode(veterinario.getPassword()));

                Role roles = roleRepo.findByName("VETERINARIO").get();
                userEntity.setRoles(List.of(roles));

                return userRepo.save(userEntity);
        }
}
