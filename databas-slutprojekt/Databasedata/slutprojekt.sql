/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

INSERT INTO `accounts` (`id`, `owner_id`, `created`, `balance`, `account_no`, `account_name`) VALUES
(1, 7, '2023-06-02', 4000, '2332 4444 6777 2335', 'Markus första konto');
INSERT INTO `accounts` (`id`, `owner_id`, `created`, `balance`, `account_no`, `account_name`) VALUES
(3, 1, '2023-06-02', 2000, '2555 2354 6786 8923', 'Ruben första konto');
INSERT INTO `accounts` (`id`, `owner_id`, `created`, `balance`, `account_no`, `account_name`) VALUES
(4, 1, '2023-06-02', 400, '1243 6784 3465 7875', 'Ruben andra konto');
INSERT INTO `accounts` (`id`, `owner_id`, `created`, `balance`, `account_no`, `account_name`) VALUES
(5, 2, '2023-06-02', 2300, '2343 5642 7645 2345', 'Anas första konto'),
(6, 2, '2023-06-02', 500, '1236 6723 6573 1235', 'Anas andra konto'),
(7, 3, '2023-06-02', 2600, '2345 6764 2346 7664', 'Nikolina första konto'),
(8, 3, '2023-06-02', 500, '1242 6573 1235 8756', 'Nikolina andra konto'),
(9, 4, '2023-06-02', 500, '2315 6745 2342 7567', 'Enes första konto'),
(10, 4, '2023-06-02', 800, '3242 6756 1231 6456', 'Enes andra konto'),
(12, 6, '2023-06-02', 800, '6546 7563 8673 1231', 'Albina andra konto'),
(15, 6, '2023-06-02', 5000, '6544 2342 8678 4322', 'Albina fjärde konto');

INSERT INTO `transactions` (`id`, `created`, `amount`, `sender_acc_id`, `receiver_acc_id`) VALUES
(1, '2023-05-02', 500, 1, 12);
INSERT INTO `transactions` (`id`, `created`, `amount`, `sender_acc_id`, `receiver_acc_id`) VALUES
(2, '2023-06-02', 500, 1, 13);
INSERT INTO `transactions` (`id`, `created`, `amount`, `sender_acc_id`, `receiver_acc_id`) VALUES
(3, '2023-06-02', 200, 11, 4);
INSERT INTO `transactions` (`id`, `created`, `amount`, `sender_acc_id`, `receiver_acc_id`) VALUES
(4, '2023-06-02', 300, 12, 5),
(5, '2023-06-02', 100, 11, 10);

INSERT INTO `users` (`id`, `birthdate`, `phone`, `email`, `name`, `address`, `password`, `created`) VALUES
(1, '194509195857', '04852599473', 'RubenSoderberg@teleworm.us', 'Ruben Söderberg', 'Långlöt 42', 'c525ca2a0f804ed4b9784dcd6abae13247de75946e0e6837d27898bce532062c075d24b5d1221f1348cdc687888620b8', '2023-06-02');
INSERT INTO `users` (`id`, `birthdate`, `phone`, `email`, `name`, `address`, `password`, `created`) VALUES
(2, '195101172772', '085611400', 'AnasAxelsson@teleworm.us', 'Anas Axelsson', 'Nils Grises Sträte 60', 'b0531381b251eb7405259793193b3f0fc226c520df3254f937c0d8b9cc77c6977e6eb325a3ce71d12dc7c1c4bd67b4db', '2023-06-02');
INSERT INTO `users` (`id`, `birthdate`, `phone`, `email`, `name`, `address`, `password`, `created`) VALUES
(3, '199705076488', '05288919875', 'NikolinaPetersson@dayrep.com', 'Nikolina Petersson', 'Ellenö 7', 'd549da4f940d87077341e8bae21d74038bd78606be6dc43e66e12d91659dc789796375c7fe27ef5e1313595c8d848b57', '2023-06-02');
INSERT INTO `users` (`id`, `birthdate`, `phone`, `email`, `name`, `address`, `password`, `created`) VALUES
(4, '196301031255', '04117992220', 'EnesSoderstrom@teleworm.us', 'Enes Söderström', 'Anders Sadelmakares Gränd 75', '2c94c5b49366f1871c4abe2b418eb3b42ae3f5cbfec38d0e6bdd29650b277d33682cefd52d342fb2d4e946119bc69a09', '2023-06-02'),
(6, '197911132368', '09612765990', 'AlbinaIsaksson@dayrep.com', 'Albina Isaksson', 'Barrgatan 28', 'f8b17e4dc56602a55b6adacf1e0da8041479334e280280a34d2a7c192a3d36325e46398b228fdfa5731a9edb7d782a2c', '2023-06-02'),
(7, '199511149016', '0708653845', 'Markus@email.com', 'Markus Johansson', 'Gratänggatan 23', '16fd870cc31de1cb31ec96da109c9222ca25c96e2316b1ef4371403a538db2066126cb952ae001594b008c365875ff9b', '2023-06-02');


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;